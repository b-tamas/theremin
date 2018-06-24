# Theremin-like digital audio instrument

Digital theremin-like instrument using ultrasonic distance sensors and music loops

## Getting Started

The regular theremin [LINK](https://en.wikipedia.org/wiki/Theremin) is controlled by the distance between the hand/finger and the antenna. This project aims to replaces the distance sensing using ultrasonic sensors and also add digital features like looping.
The distance between the sensor and the hand can signal the mood or tempo of the loop instead of frequency or volume.
The artist can use both hands, or two persons can play on this instrument wiht one-one hands. 
The mood control might use simple patterns like:
* up - up  -> upbeat, loud solo
* low - low -> quiet part or solo of some intsrument
* low - high -> chorus
* very close to sensor -> final accord(s)
  
Alternatively, the rythmic movement of the hands can give tempo signal (e.g. hip-hop).
The movement is FFT analyzed and the appropriate loop is selected. Note that this will not allow smooth speed-up/slow-down as that would require MIDI. The artist is supposed to align to the selected rythm actually.

### Prerequisites

An Arduino is required to feed information about distance sensors.
RPI was used for the early prototype, any Linux might be suitable. No GPIO or specific RPI feature was used so far.

### Installing

TBD

## Running the tests

TBD


## Deployment

sudo apt-get install librtmidi-dev
sudo apt-get install librtmidi-dbg
sudo apt-get install librtmidi-dev
sudo apt-get source librtmidi-dev
sudo apt-get source librtmidi-dbg
sudo apt-get source librtmidi-dev
sudo apt-get source librtmidi2
g++ -Wall -D__LINUX_ALSA__ -o midiout midiout.cpp RtMidi.cpp -lasound -lpthread  #TODO


## Built With

https://github.com/thestk/rtaudio

## Contributing

TBD

## Versioning

TBD 

## Authors

TBD

## License

Components from https://github.com/thestk/rtaudio

## Acknowledgments

TBD
