/**
 *  Thermostat Child Device
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "Thermostat Child Device", namespace: "KeithR26", author: "Keith Riley", vid: "generic-thermostat") {
		capability "Thermostat"
//		capability "Actuator"
//		capability "Sensor"
//        capability "Refresh"

	
	attribute	"coolingSetpoint", "string"
	attribute	"coolingSetpointRange", "string"
	attribute	"heatingSetpoint", "string"
	attribute	"heatingSetpointRange", "string"
	attribute	"schedule", "string"
	attribute	"temperature", "string"
	attribute	"supportedThermostatFanModes", "string"
	attribute	"supportedThermostatModes", "string"
	attribute	"thermostatMode", "string"
	attribute	"thermostatFanMode", "string"
	attribute	"thermostatOperatingState", "string"
	attribute	"thermostatSetpoint", "string"
	attribute	"thermostatSetpointRange", "string"
		
	command		"updated"	
	
	}

	tiles {
		multiAttributeTile(name:"switch", type: "lighting", width: 3, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff", nextState:"turningOn"
				attributeState "on", label: '${name}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#00A0DC", nextState:"turningOff"
				attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#00A0DC", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"turningOn"
			}
		}
     standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
		    state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
        }
	}
}

void updated() {
	log.debug "updated() called in Thermostat Child device"
	
	sendEvent(name: "coolingSetpoint", value: "78", isStateChange: true, displayed: true, descriptionText: "coolingSetpoint is 78")
	sendEvent(name: "coolingSetpointRange", value: "[78,78]", isStateChange: true, displayed: true, descriptionText: "coolingSetpointRange is ??")
	sendEvent(name: "heatingSetpoint", value: "68", isStateChange: true, displayed: true, descriptionText: "heatingSetpoint is 68")
	sendEvent(name: "heatingSetpointRange", value: "[60,75]", isStateChange: true, displayed: true, descriptionText: "heatingSetpointRange is ??")
	sendEvent(name: "schedule", value: "{time: \"11:21\"}", isStateChange: true, displayed: true, descriptionText: "schedule is ??")
	sendEvent(name: "supportedThermostatFanModes", value: "[off]", isStateChange: true, displayed: true, descriptionText: "supportedThermostatFanModes is 55")
	sendEvent(name: "supportedThermostatModes", value: "[heat,off]", isStateChange: true, displayed: true, descriptionText: "supportedThermostatModes is 55")
	sendEvent(name: "temperature", value: "72", isStateChange: true, displayed: true, descriptionText: "temperature is 72")
	sendEvent(name: "thermostatMode", value: "auto", isStateChange: true, displayed: true, descriptionText: "thermostatFanMode is auto")
	sendEvent(name: "thermostatFanMode", value: "off", isStateChange: true, displayed: true, descriptionText: "thermostatMode is auto")
	sendEvent(name: "thermostatOperatingState", value: "idle", isStateChange: true, displayed: true, descriptionText: "thermostatOperatingState is idle")
	sendEvent(name: "thermostatSetPoint", value: "75", isStateChange: true, displayed: true, descriptionText: "thermostatSetPoint is 55")
	sendEvent(name: "thermostatSetpointRange", value: "[60,83]", isStatusChange: true, displayed: true, descriptionText: "thermostatSetpointRange is 55")
}

void on() {
	parent.childOn(device.deviceNetworkId)
}

void refresh() {
	parent.childRefresh(device.deviceNetworkId)
}


void auto() {
	log.debug "auto() called in Thermostat child"
}

void cool() {
	log.debug "cool() called in Thermostat child"
}

void emergencyHeat() {
	log.debug "emergencyHeat() called in Thermostat child"
}

void fanAuto() {
	log.debug "fanAuto() called in Thermostat child"
}

void fanCirculate() {
	log.debug "fanCirculate() called in Thermostat child"
}

void fanOn() {
	log.debug "fanOn() called in Thermostat child"
}

void heat() {
	log.debug "heat() called in Thermostat child"
}

void off() {
	log.debug "off() called in Thermostat child"
}

void setCoolingSetpoint(temperature) {
	log.debug "setCoolingSetpoint(${temperature}) called in Thermostat child"
}

void setHeatingSetpoint(temperature) {
	log.debug "setHeatingSetpoint(${temperature}) called in Thermostat child"
	parent.childSetHeatingSetpoint(device.deviceNetworkId, temperature)
}

void setSchedule(jsonObject) {
	log.debug "setSchedule(${jsonObject}) called in Thermostat child"
}

void setThermostatFanMode(fanMode) {
	log.debug "setThermostatFanMode(${fanMode}) called in Thermostat child"
}

void setThermostatMode(thermostatMode) {
	log.debug "setThermostatMode(${thermostatMode}) called in Thermostat child"
}
/*
name: Thermostat
status: deprecated
attributes:
  coolingSetpoint:
    type: NUMBER
  coolingSetpointRange:
    schema:
      type: object
      properties:
        value:
          type: array
          items:
            -  $ref: TemperatureValue
            -  $ref: TemperatureValue
          minItems: 2
          maxItems: 2
      required:
        - value
    type: VECTOR3
  heatingSetpoint:
    type: NUMBER
  heatingSetpointRange:
      type: object
      properties:
        value:
          type: array
          items:
            -  $ref: TemperatureValue
            -  $ref: TemperatureValue
          minItems: 2
          maxItems: 2
      required:
        - value
    type: VECTOR3
  schedule:
    type: JSON_OBJECT
  temperature:
    type: NUMBER
  thermostatFanMode:
    type: ENUM
    values:
      - auto
      - circulate
      - followschedule
      - 'on'
    setter: setThermostatFanMode
    enumCommands:
      - command: fanAuto
        value: auto
      - command: fanCirculate
        value: circulate
      - command: fanOn
        value: 'on'
  supportedThermostatFanModes:
    schema:
      type: object
      properties:
        value:
          type: array
          items:
            $ref: ThermostatFanMode
    type: JSON_OBJECT
  thermostatMode:
    type: ENUM
    values:
      - auto
      - eco
      - rush hour
      - cool
      - emergency heat
      - heat
      - 'off'
    setter: setThermostatMode
    enumCommands:
      - command: auto
        value: auto
      - command: cool
        value: cool
      - command: emergencyHeat
        value: emergency heat
      - command: heat
        value: heat
      - command: 'off'
        value: 'off'
  supportedThermostatModes:
    schema:
      type: object
      properties:
        value:
          type: array
          items:
            $ref: ThermostatMode
    type: JSON_OBJECT
  thermostatOperatingState:
    type: ENUM
    values:
      - cooling
      - fan only
      - heating
      - idle
      - pending cool
      - pending heat
      - vent economizer
  thermostatSetpoint:
    type: NUMBER
  thermostatSetpointRange:
    schema:
      type: object
      properties:
        value:
          type: array
          items:
            -  $ref: TemperatureValue
            -  $ref: TemperatureValue
          minItems: 2
          maxItems: 2
    type: VECTOR3


  setCoolingSetpoint:
      type: NUMBER
  setHeatingSetpoint:
      type: NUMBER
  setSchedule:
      type: JSON_OBJECT
  setThermostatFanMode:
      type: ENUM
      values:
        - auto
        - circulate
        - followschedule
        - 'on'
  setThermostatMode:
      type: ENUM
      values:
        - auto
        - eco
        - rush hour
        - cool
        - emergency heat
        - heat
        - 'off'
*/