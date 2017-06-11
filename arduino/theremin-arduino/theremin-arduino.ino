#include <NewPing.h>

#include <Boards.h>
#include <SoftwareSerial.h>// import the serial library
#include <EEPROM.h>



//BLUETOOTH
SoftwareSerial BTSerial(10, 11); // RX, TX
int ledpin = 13; // led on D13 will show blink on / off
int BluetoothData; // the data given from Computer
char c = ' ';
boolean NL = true;
String bluetoothrx;

//eeprom block size for logging
#define eepromblock 10  //valid values 5,10,15,20,25,50,100

#define pwmpinA 9
#define pwmpinB 13  //shared with onboard LED

#define adcpin0 A0
#define adcpin1  A1
#define adcpin2  A2
#define adcpin3  A3

#define inpin0  3
#define inpin1  4
#define inpin2  5

#define outpin1 6
#define outpin2 7


//sonar pins and defines
#define TRIGGER_PIN  2
#define ECHO_PIN     3
#define MAX_DISTANCE 200
#define SONAR_NUM 4


NewPing sonar[SONAR_NUM] = { 
  NewPing(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE),
  NewPing(4, 5, MAX_DISTANCE),
  NewPing(6, 7, MAX_DISTANCE),
  NewPing(8, 9, MAX_DISTANCE)
};




/////////////////////////////////////////////////////////
int verbose = 0;
int incomingByte;
int s = 1;
int eepromptr = 0;
byte value;
String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete

int sensorState;

int uptimeseconds;
int cnt;
////////////////////////////////////////////////////////

int enableVerbosity(int* verb) {
  *verb = 1;
  print("*** Verbose logging enabled ***");
}

int disableVerbosity(int* verb) {
  *verb = 0;
  print("*** Verbose logging disabled ***");
}

void serialEvent() {
  while (Serial.available()) {
    char inChar = (char)Serial.read();

    if (inChar == '\n' || inChar == '\r' ) {
      stringComplete = true;
      return;
    }
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
  }
}

void serialEventRun(void) {
  if (Serial.available()) serialEvent();
}

int dbg (String msg) {
  if (verbose) {
    print(msg);
  }
}

void eepromclear( int* ptr) {
  for (int i = 0; i < 512; i++)
    EEPROM.write(i, 0);
  *ptr = 0;
}

void eepromwrite(String str, int* ptr) {
  if (*ptr >= 500) {  //fixme
    *ptr = 0;
  }

  for (int i = 0; i < (eepromblock - 1); i++) { // a ptr altal mutatott helyre bemasol X elemet.
    EEPROM.write(i + *ptr, int(str[i]));
  }
  //EEPROM.write(eepromblock+*ptr, int(13));
  *ptr += eepromblock;
}

void    dumpeeprom() {
  Serial.println("Content of EEPROM, RAW: ");
  for (int i = 0; i < 500; i++)  {
    value = EEPROM.read(i);
    Serial.print(i);
    Serial.print("\t");
    Serial.print(value, DEC);
    Serial.println();
  }
}

void    dumpeepromblocks() {
  Serial.println("Content of EEPROM, ASCII, blocks: ");

  for (int i = 0; i < 500; i++)  {
    value = EEPROM.read(i);
    Serial.write(value);
    if ( (i + 1) % eepromblock == 0 ) {  // sortordeles
      Serial.println();
    }  //if
  } //for
}  //function

void    dumpeepromasciiraw() {
  Serial.println("Content of EEPROM, ASCII RAW: ");

  for (int i = 0; i < 500; i++)  {
    value = EEPROM.read(i);
    Serial.write(value);
  } //for
}  //function

ISR(TIMER1_COMPA_vect)          // timer compare interrupt service routine
{
  cnt++;
  if (cnt == 10) {
    uptimeseconds += 1;
    cnt = 0;
  }
}


int print (String message) {
  //printing to both interface
  Serial.println(message);
  BTSerial.println(message);
}

int log (String message) {
  String S = "";
  S = "Log: " + uptimeseconds;   S = S + " ";    S = S + message;
  Serial.println(S);
}

int logcmd (String message) {
  Serial.print("Cmd: ");
  Serial.print(" ");
  Serial.println (message);
}

void ReceiveBluetoothMsg() {

  if (BTSerial.available()) {
    char inChar = BTSerial.read();
    Serial.print(inChar);

    if (inChar == '\n' || inChar == '\r' ) {
      // if the incoming character is a newline, set a flag
      // so the main loop can do something about it:
      stringComplete = true;
      return;
    }
    inputString += inChar;
  }//if available
}

void HandleIncomingMsg() {
  logcmd(inputString);

  //https://en.wikipedia.org/wiki/ASCII
  int delimindex = inputString.indexOf(" ");
  Serial.println(delimindex);
  String command = inputString.substring(0, delimindex);
  String arg = inputString.substring(delimindex + 1);

  if (command.equals("out1")) {
    log("out1 detected");
    Serial.println(arg);
    digitalWrite(outpin1, int(arg.toInt()));
  }

  if (command.equals("out2")) {
    log("out2 detected");
    Serial.println(arg);
    digitalWrite(outpin2, int(arg.toInt()));
  }

  if (command.equals("adc")) {
    log("adc detected");

    print(String(analogRead(adcpin0)));
    print(String(analogRead(adcpin1)));
    print(String(analogRead(adcpin2)));
    print(String(analogRead(adcpin3)));
  }

  if (command.equals("in")) {
    log("in detected");

    Serial.println(digitalRead(inpin0));
    Serial.println(digitalRead(inpin1));
    Serial.println(digitalRead(inpin2));
  }

  if (command.equals("pwma")) {
    log("pwma detected");
    Serial.println(int(arg.toInt()));
    analogWrite(pwmpinA, int(arg.toInt()));
  }

  if (command.equals("pwmb")) {
    log("pwmb detected");
    Serial.println(int(arg.toInt()));
    analogWrite(pwmpinB, int(arg.toInt()));
  }

  if (command.equals("r")) {  // r, dump eeprom ascii raw     #FIXME, might be incorrect
    log("r char detected, dump eeprom decimal");
    dumpeeprom();
  }

  if (command.equals("L"))  {   //L, enable verbose logging
    log("L char detected");
    enableVerbosity(&verbose);
  }

  if (command.equals("l")) {  // l, disable  verbose logging
    log("l char detected");
    disableVerbosity(&verbose);
  }

  if (command.equals("c")) {  // c, clear eeprom
    log("c char detected, clear eeprom");
    eepromclear(&eepromptr);
  }
  if (command.equals("f")) {  // f, fake message
    log("f char detected, fake message written to eeprom");
    eepromwrite("fakemsg", &eepromptr);
  }

  if (command.equals("d")) { // d, dump eeprom
    log("d char detected, dump eeprom ascii raw");
    dumpeepromasciiraw();
  }

  if (command.equals("D")) { // D, dump eeprom ascii blocks
    log("D char detected, dump eeprom ascii blocks");
    dumpeepromblocks();
  }

 
  if (command.equals("help")) {
    print("*****");
    print("help - this help");
    print("*****");
  }

  // clear the string:
  inputString = "";
  stringComplete = false;
}





int out(String msg, String details, char dbg, char printout, char eeprom) 
{

if (eeprom == 1 ) {
    eepromwrite(msg, &eepromptr);   
    //details field is skipped here
}

  if (printout == 1 ) {
    Serial.println(msg);
    BTSerial.println(msg);
    return 1;
  }
  
  if (dbg == 1 ) {
    if (verbose) {
        Serial.println(msg);
        BTSerial.println(msg);
        Serial.println(details);
        BTSerial.println(details);
        }
  else
      {
        Serial.println(msg);
        Serial.println(details);
      }
  }  
}

//*****************************************************************

void setup() {
  BTSerial.begin(9600);
  BTSerial.println("Welcome on Bluetooth");
  BTSerial.println("ask help for usage");
  pinMode(ledpin, OUTPUT);
  // end of bluetooth initialization

  Serial.begin(9600);
  delay(1000);
  /*while (!Serial) {
    ;
    } // wait for serial port to connect. Needed for Leonardo only  */
  inputString.reserve(200);

  //generic pins
  pinMode(pwmpinA, OUTPUT);
  pinMode(pwmpinB, OUTPUT);
  randomSeed(analogRead(7));

  log("Starting...");

  
}

void loop() {
  ReceiveBluetoothMsg();
  if (stringComplete) {
    HandleIncomingMsg();
  }
  
Serial.print(sonar[0].ping_cm());
Serial.print(",");
Serial.print(sonar[1].ping_cm());
Serial.print(",");
Serial.print(sonar[2].ping_cm());
Serial.print(",");
Serial.print(sonar[3].ping_cm());
Serial.println("");

delay(100);

  
}











