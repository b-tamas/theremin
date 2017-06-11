#include <NewPing.h>
#include <Boards.h>

#define TRIGGER_PIN_1  2
#define ECHO_PIN_1     3
#define TRIGGER_PIN_2  4
#define ECHO_PIN_2     5
#define TRIGGER_PIN_3  6
#define ECHO_PIN_3     7
#define TRIGGER_PIN_4  8
#define ECHO_PIN_4     9
#define MAX_DISTANCE 200

NewPing sonar[4] = { 
  NewPing(TRIGGER_PIN_1, ECHO_PIN_1, MAX_DISTANCE),
  NewPing(TRIGGER_PIN_2, ECHO_PIN_2, MAX_DISTANCE),
  NewPing(TRIGGER_PIN_3, ECHO_PIN_3, MAX_DISTANCE),
  NewPing(TRIGGER_PIN_4, ECHO_PIN_4, MAX_DISTANCE)
};

void setup() {
  Serial.begin(9600);
  delay(1000);
}

void loop() {
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











