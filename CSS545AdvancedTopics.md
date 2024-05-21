# Bluetooth Device Discovery - Communication Protocols
### Sarah Martel - CSS 545

## Introduction
Bluetooth devices are everywhere. Chances are, the device on which you are reading this paragraph has Bluetooth capabilities. In only its third decade of existence, Bluetooth has evolved from its simple beginnings in the audio world to transmitting all sorts of information quickly, including sensor data and location information. There are several categories where Bluetooth plays an important role and is thus a high priority. This paper offers a discussion of the applications where Bluetooth thrives, how Bluetooth is implemented to solve technological problems, and an analysis of the current solutions. It concludes with an idea for a solution to a limitation of the current Bluetooth technology. 

The 2024 Bluetooth Market Update identifies four domains where Bluetooth technology is focused on fulfilling technological needs. These domains include:
*	Audio streaming
*	Data transfer
*	Location services
*	Device networks

It is worth noting that the quantity and variety of Bluetooth devices has continued to grow over the years, and is projected to continue, with the largest increase in Bluetooth LE (low energy) devices.

## Industry Needs

Bluetooth is more than just earbuds and wireless speakers. The array of Bluetooth devices has expanded since Bluetooth made its debut in the consumer market in 1999 with a wireless headset for a mobile phone (androidauthority.com). Audio streaming devices also include car audio systems for hands-free calling, and streaming audio to hearing aids.

Another use case for Bluetooth is for data transfer. This includes computer accessories such as mice and keyboards, fitness trackers, and smartwatches. Bluetooth LE is also used in the medical field, in monitoring machines and imaging machines. VR wearables, smart glasses and game controllers are even more examples of devices that use Bluetooth for data transfer. These devices are all projected to grow in the near future.

GPS exists for determining location, but Bluetooth has some advantages over GPS, depending on the need. Tracking a product’s location inside a warehouse, Airtags, and unlocking a car with digital car keys, are cases where having a very accurate location is crucial. Besides the high accuracy, Bluetooth is more affordable and has longer battery life compared to GPS devices (link-labs.com). 

The fourth industry that Bluetooth is a priority in is in device networks. This includes smart home systems, networked lighting control (NLC) systems, sensors such as temperature and light, and electronic shelf labels (ESL) which are being used at retail stores. Almost half of the smart home devices are smart appliances, with smart lighting coming in second (bluetooth.com market report).

## Current Solutions

It is clear that in one way or another, and arguably more than one, that Bluetooth technology affects each of us every day. The power and success of Bluetooth technology consists of two main protocols: Bluetooth Classic and Bluetooth Low Energy (LE). While used less nowadays in mobile apps, Bluetooth Classic is primarily used in Bluetooth headphones and speakers, and car audio systems (bluetooth.com). Much of the communication done with apps and IOT devices uses BluetoothLE. As stated in the name, it uses low energy levels compared to Bluetooth Classic. Besides using less energy, it has the ability to work with mesh networks and can be used to determine a device’s position (bluetooth.com). These features are not available in Bluetooth Classic.

Let’s look more closely at the features of BluetoothLE. It uses only 10% of the amount of energy that classic Bluetooth uses, because it remains in an inactive state until a connection is initiated (sensolus.com). It’s intended to be used short-range, so it can get away with using less energy. Since it works much faster than Bluetooth classic (100 ms in classic vs a few ms for BLE) it in turn uses less energy (sensolus.com).

Because BLE uses low energy, this works well for short range communication. If you only need to transmit a small amount of information at a short distance, BLE is a better solution over Bluetooth Classic.  A short distance, required for BLE communication, would be about 10 meters or less, give or take any obstacles in the way of the devices trying to communicate (sciencedirect.com). This is perfect for medical devices used in hospitals, which only need to transmit information about the patient to the nearby machine. It’s fast and effective, which is especially important in the medical field. BLE eliminates the need for cables in medical imaging devices, and delivers continuous connectivity (bluegoatcyber.com).

Whether you are using BluetoothLE or Bluetooth Classic, there are some basic steps in order to get communication going. The following section explains some of the code-related components necessary to include in an app that uses Bluetooth.

Bluetooth profiles and services - Each device that the app connects to needs to use a different Bluetooth profile, or interface specified in the Bluetooth documentation (guide to bluetooth). Connecting to a heart rate sensor vs headphones requires separate profiles and services. Bluetooth services are broad categories, so a heart rate service would specify the required functionalities for sending heart rate data using Bluetooth. Then the code would need to include the heart rate profile, a document that defines the specifications on how to accomplish transmitting the data. 

Dealing with data - Data items are managed through the use of characteristics (guide to bluetooth). Profiles can have multiple characteristics. Using the heart rate profile as an example, characteristics would include the Heart Rate Measurement characteristic, Body Sensor Location characteristic and the Heart Rate Control Point characteristic (Heart Rate Profile PDF).

## Critical Analysis

There appears to be a lot going for Bluetooth technology, especially today with the broad usage of BLE devices. Every computer and smartphone has Bluetooth capabilities, and we use accessories with our computers and smartphones that communicate with Bluetooth. It has dozens of uses, across multiple industries and for personal use. One Bluetooth peripheral can be used by many central devices, and a central can have several peripheral devices paired with it.

This is a con/limitation, and its only gotten better over the years, so its possible in the next decade that this won’t exist, but Bluetooth is limited by distance. If I walk down the hall and out of my room, my earbuds won’t receive a signal from my phone in the room. Wifi works farther and can transmit more data at faster speeds. Of course, each technology has uses that it is better for over others. But it would be convenient if Bluetooth could be used for larger data applications like streaming movies.

One downside with Bluetooth, and this is from personal experience, is that devices don’t always connect right away, or stay connected. Sometimes my Bluetooth earbuds don’t connect as easily as I’d like them to. But it only takes a second to plug in wired earbuds, and they work right away, without having to pair them. A second downside compared to its wired counterpart is that Bluetooth devices need to be powered with batteries or recharged. It’s why I opted for a wired keyboard. I did, however, choose a Bluetooth mouse for its portability, even if I do need to charge it. 

There is a limitation to the amount of Bluetooth devices that can be connected to an Android or iPhone at the same time. Android and iPhones can have up to 7 connected devices, but for iPhone, only three or four can be actively connected to Bluetooth at a time (soundcore.com). While this might sound like a limitation, it can be seen as a pro for security. Imagine if a second pair of headphones were able to connect to your phone and listen in on your conversation, like listening in on someone’s landline conversation from the other room? Thankfully, Bluetooth audio communication is limited to one device at a time (soundcore.com).

## Solution Proposal

I would like to see a solution to the limitation where only one audio device can be active in Bluetooth communication. There could be situations where you’d want to have someone else listen to music with you, or watch a show, without having to use only one earbud. So if there was a way to approve the second device, then it could work. Maybe you need a companion app, and can have a list of approved earbuds that are allowed to listen. So the other person couldn’t just join in at any time, you’d have to consent to the connection through the companion app, and enter your password. It would also need to show up with an icon that specifies that a second device is connected. An alternative would be that the earbuds are sold in pairs and are only compatible with their mate. If you lose one earbud, you’d have to replace all 4 because they are programmed together. 

# Sources

https://www.androidauthority.com/history-bluetooth-explained-846345/ 

https://www.link-labs.com/blog/bluetooth-indoor-positioning-vs-gps 

https://www.sensolus.com/how-does-bluetooth-low-energy-ble-work/ 

https://www.sciencedirect.com/topics/computer-science/bluetooth-low-energy 

https://bluegoatcyber.com/blog/the-importance-of-ble-in-medical-devices-cybersecurity/ 

https://us.soundcore.com/blogs/headphones/how-many-bluetooth-connections-at-once-across-devices 

https://www.bluetooth.com/learn-about-bluetooth/tech-overview/ 

https://www.bluetooth.com/specifications/specs/ shows the official list of bluetooth profiles and services

https://www.bluetooth.com/blog/a-developers-guide-to-bluetooth/ 

https://www.bluetooth.com/specifications/specs/heart-rate-profile-1-0/ 