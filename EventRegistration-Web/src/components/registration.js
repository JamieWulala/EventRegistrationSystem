import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ParticipantDto (name) {
  this.name = name
  this.events = []
}

function EventDto (name, date, start, end) {
  this.name = name
  this.eventDate = date
  this.startTime = start
  this.endTime = end
}

function RegistrationDto (participant,event) {
  this.participant = participant
  this.event = event
}

export default {
  name: 'eventregistration',
  data () {
    return {
      participants: [],
      newParticipant: '',
      errorParticipant: '',
      events:[],
      newEvent:'',
      errorEvent:'',
      errorRegistration:'',
      selectedParticipant:'',
      selectedEvent:'',
      endTime:'',
      startTime:'',
      date:'',
      eventName:'',
      response: []
    }
  },
  //...
  created: function () {
    // Initializing participants from backend
      AXIOS.get(`/participants`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.participants = response.data
        
      })
      .catch(e => {
        this.errorParticipant = e;
      });

      AXIOS.get(`/events`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.events = response.data
      })
      .catch(e => {
        this.errorEvent = e;
      });


  },

  methods: {
    createParticipant: function (participantName) {
      AXIOS.post(`/participants/`+participantName, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.participants.push(response.data)
        this.newParticipant = ''
        this.errorParticipant = ''
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
        this.errorParticipant = errorMsg
      });
    },

    createEvent: function (eventName,eventDate,eventST,eventET) {
      AXIOS.post(`/events/`+eventName+`?date=`+eventDate+`&startTime=`+eventST+`&endTime=`+eventET)
      //eg http://localhost:8088/events/MarathonRunning?date=2018-09-23&startTime=07:00&endTime=18:00
      .then(response => {
        // JSON responses are automatically parsed.
        this.events.push(response.data)
        this.newEvent = ''
        this.errorEvent = ''
      })
      .catch(e => {
        var errorMsg = e.response
        console.log(errorMsg)
        this.errorEvent = errorMsg
      });
      console.log("Event "+eventName+" is created.")
    },

    register: function (participant,event) {
      AXIOS.post(`/register?participant=`+participant+`&event=`+event)
      //eg http://localhost:8088/register?participant=John&event=MarathonRunning

      .then(response => {
        // JSON responses are automatically parsed.
        this.newRegistration = ''
        this.errorRegistration = ''
        AXIOS.get(`/participants`)
        .then(response => {
        // JSON responses are automatically parsed.
        this.participants = response.data
        
      })
      .catch(e => {
        this.errorParticipant = e;
      });
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
        this.errorRegistration = errorMsg
      });
      console.log("register "+participant+" to "+event)
    }
  }
}