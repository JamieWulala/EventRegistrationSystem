<template>
  <div id="eventregistration">
    <h2>Participants</h2>
    <table>
      <tr class="title">
        <td>Participants</td>
        <td>Their Events</td>
      </tr>
      <tr v-for="participant in participants" >
          <td>{{ participant.name }}</td>
          <td>
            <ul>
              <li v-for="event in participant.events">
                {{event.name}}
              </li>
            </ul>
          </td>
      </tr>
    <!-- ... -->
      <tr>
        <td>
            <input type="text" v-model="newParticipant" placeholder="Participant Name">
        </td>
        <td>
            <button @click="createParticipant(newParticipant)">Create Participant</button>
        </td>
      </tr>
    </table>
    <p>
      <span v-if="errorParticipant" style="color:red">Error: {{errorParticipant}} </span>
    </p>


    <h2>Events</h2>
      <tr class = "title">
        <td>Event Name</td>
        <td>Event Date</td>
        <td>start Time</td>
        <td>end Time</td>
      </tr>
      <tr v-for="event in events" >
          <td>{{ event.name }}</td>
          <td>{{ event.eventDate }} </td>
          <td>{{ event.startTime }} </td>
          <td>{{ event.endTime }}</td>
      </tr>

      <tr>
        <td>
            <input type="text" v-model="eventName" placeholder="Event Name" value = "newEvent" >
        </td>
        <td>
            <input type="date" v-model="date" placeholder="Date" value = "2017-12-31"> 
        </td>
        <td>
            <input type="time" v-model="startTime" placeholder="HH:mm" value = "19:00:00">
        </td>
        <td>
            <input type="time" v-model="endTime" placeholder="HH:mm" value="21:00:00">
        </td>

        <td>
            <button @click="createEvent(eventName,date,startTime,endTime)">Create Event</button>
        </td>
      </tr>
    </table>
    <p>
      <span v-if="errorEvent" style="color:red">Error: {{errorEvent}} </span>
    </p>

    <h2>Register</h2>
    <tr class ="title">
      <td>Event</td>
      <td>Participant</td>
    </tr>

    <tr>
        <td>
            <select v-model="selectedEvent">
              <option disabled value="" > select one Event</option>
              <option v-for="event in events" > {{event.name}} </option>
            </select>
        </td>
        <td>
            <select v-model="selectedParticipant">
              <option disabled value=""> select one Participant</option>
              <option v-for="participant in participants"> {{participant.name}} </option>
            </select>    
        </td>

        <td>
            <button @click="register(selectedParticipant,selectedEvent)">Register</button>
        </td>

    </tr>

    </table>
    <p>
      <span v-if="errorRegistration" style="color:red">Error: {{errorRegistration}} </span>
    </p>
  </div>
</template>

<script src="./registration.js">
</script>

<style>
  #eventregistration {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }

  .title {
    font-family: sans-serif;
    color: #26466F;
    font-size: 20px;
    font-style: italic;
  }
</style>