<script setup>
import { ref, nextTick, reactive, onBeforeMount } from 'vue';
import { events} from '../../js/event'
import IconUser from "../icons/IconUser.vue";
import IconEmail from '../icons/IconEmail.vue';
import IconCalendar from '../icons/IconCalendar.vue';
import dayjs from 'dayjs';
import IconTime from '../icons/IconTime.vue';
import IconArrowLeft from '../icons/IconArrowLeft.vue';
import VFormInput from '../Base/VFormInput.vue';

const emits = defineEmits(['emitBack', 'emitSubmitForm'])
const props = defineProps({
    stage: {
        type: Number,
        require: true,
    },
    selectedCategory:{
        type: Object,
        default: {}
    }
})


const inputName = ref(null)
const inputEmail = ref(null)
const focusOn = ref(null)

const inform = reactive({
    status: false,
    name: { 
        value: '', 
        errors: [], 
        isError: () => inform.name.errors.length !== 0,
        checkError() {
            this.errors = []
            checker.isBlank(this.value) ? this.errors.push('Name cannot be blank') : ''
            inform.checkFormComplete()
        } 
    },
    email: { 
        value: '', 
        errors: [], 
        isError: () => inform.email.errors.length !== 0,
        checkError() {
            this.errors = []
            checker.isBlank(this.value) ? this.errors.push('Email cannot be blank') : ''
            !checker.isEmail(this.value) ? this.errors.push('Invalid email address') : ''
            inform.checkFormComplete()
        } 
    },
    note: { 
        value: null, 
        errors: [],
        isError: () => inform.note.errors.length !== 0,
        checkError() {
            this.errors = []
            if(this.value == null) return
            checker.isBlank(this.value) ? this.errors.push('Note must not be white space') : ''
            inform.checkFormComplete()
        } 
    },
    datetime: {
        time: null,
        date: null,
        errors: [],
        isError: () => inform.datetime.errors.length !== 0,
         checkError() {
            this.errors = []
            //if some of 2 null return
            if(!this.date || !this.time) return
            !checker.isFuture(this.date + this.time) ? this.errors.push('Date&Time must be a future date') : ''
            events.checkOverlap(this.date + this.time, props.selectedCategory.eventDuration, props.selectedCategory.id) 
                ? this.errors.push('Time is Overlaped') : ''
            inform.checkFormComplete()
        } 
    },
    checkFormComplete(){
        const isNameComplete = !this.name.isError() && this.name.value != ''
        const isEmailComplete = !this.email.isError() && this.email.value != ''
        const isNoteComplete = !this.note.isError()
        const isDateTimeComplete = !this.datetime.isError() && this.datetime.date != null && this.datetime.time != null
        this.status = isNameComplete && isEmailComplete && isNoteComplete && isDateTimeComplete
        // console.log(this.status)
    },
    send(){
        const note = this.note.value
        const informObj = {
            bookingName: this.name.value,
            bookingEmail: this.email.value,
            eventStartTime: dayjs(this.datetime.date + this.datetime.time).toJSON()
        }
        if(note) informObj.eventNotes = note

        console.log(informObj);
        emits('emitSubmitForm', informObj)
    },
    // setup(){
    //     inform.name.value = ''
    //     inform.email.value = ''
    //     inform.note.value = null
    //     inform.datetime.date = null
    //     inform.datetime.time = null
    // }
})

const checker = {
    isBlank: (string) => string.match(/^\s+$/),
    isEmail: (string) => string == '' ||string.match(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i),
    isFuture: (datetime) => dayjs(datetime).isAfter(dayjs()),
}
</script>
 
<template>
<div :class="['flex flex-col items-center justify-between']">
    <div class="flex flex-col h-full  overflow-auto ">
        <div :class="['flex']">
            <h1 :class="['text-4xl', 'text-center']">Fill Information</h1>
        </div>
        <!-- form -->
        <div class="flex flex-col my-4 w-160 gap-6">
            <div class="w-fit px-4 py-1 rounded text-white flex gap-10 text-lg bg-gradient-to-br from-blue-600 via-purple-500 to-purple-300">
                <span>{{ selectedCategory.eventCategoryName }}</span>
                <span>{{ selectedCategory.eventDuration }} minute</span>
            </div>
            <!-- Name -->
            <div class="relative flex flex-col gap-0.5">
                <label for="name" :class="['text-sm font-medium text-gray-600 pl-0.5']"> 
                    Name
                </label>
                <div :class="['relative']">
                    <!-- <label for="name" :class="['absolute cursor-text text-sm', ' ease-out duration-500  scale-70',
                focusOn === 'name'
                    ? '-top-2 left-8 bg-gray-50 px-2 scale-75 '
                    : 'inset-y-0 left-10 flex items-center scale-100 ',]"
                        @click="nextTick(() => inputName.focus())">
                        Name
                    </label> -->
                    <button @click="nextTick(() => inputName.focus())" 
                        class="absolute inset-y-0 items-center left-3">
                        <IconUser class="w-5 h-5" />
                    </button>
  
                    <input ref="inputName" type="text" maxlength="100" placeholder="Name"
                        @focusin="focusOn = 'name'" @focusout="focusOn = null"
                        @keyup="inform.name.checkError"
                        v-model="inform.name.value" 
                        :class="[inform.name.isError() ? 'form-input-error' : 'form-input', 'pl-10']">
                </div>
                <!-- desc -->
                <p class="absolute top-0 right-0 text-sm font-medium text-red-500 pl-0.5">Required</p>
                <!-- Error Noti -->
                <div v-show="inform.name.isError()" class="absolute -bottom-5 text-sm font-medium text-red-500 pl-0.5">
                    Invalid name error: 
                    <span v-for="error in inform.name.errors">{{ error }};</span>
                </div>
            </div>

            <!-- Email -->
            <div class="relative flex flex-col gap-0.5 ">
                <label for="name" :class="['text-sm font-medium text-gray-600 pl-0.5']">Email</label>
                <div :class="['relative']">
                    <button @click="nextTick(() => inputEmail.focus())" 
                        class="absolute inset-y-0 items-center left-3">
                        <IconEmail class="w-5 h-5" />
                    </button>
                    <input ref="inputEmail" type="text" maxlength="100" placeholder="Email"
                        @focusin="focusOn = 'email'" @focusout="focusOn = null"
                        @keyup="inform.email.checkError"
                        v-model="inform.email.value" 
                        :class="[inform.email.isError() ? 'form-input-error' : 'form-input', 'pl-10']">
                </div>
                <!-- desc -->
                <p class="absolute top-0 right-0 text-sm font-medium text-red-500 pl-0.5">Required</p>
                <!-- Error Noti -->
                <div v-show="inform.email.isError()" class="absolute -bottom-5 text-sm font-medium text-red-500 pl-0.5">
                    Invalid name error: 
                    <span v-for="error in inform.email.errors">{{ error }}; </span>
                </div>
            </div>

            <!-- Note -->
            <div class="relative flex flex-col gap-0.5">
                <label for="name" :class="['text-sm font-medium text-gray-600']"> 
                    Note
                </label>
                <textarea name="eventNotes" rows="5" placeholder="Note" maxlength="500"
                    @keyup="inform.note.checkError"
                    v-model="inform.note.value"
                    :class="[inform.note.isError() ? 'form-input-error' : 'form-input', 'text-sm']">
                </textarea>
                <!-- desc -->
                <p class="absolute top-0 right-0 text-sm font-medium text-gray-600 pl-0.5">Optional</p>
                <!-- Error Noti -->
                <div v-show="inform.note.isError()" class="absolute -bottom-5 text-sm font-medium text-red-500 pl-0.5">
                    Invalid note error: 
                    <span v-for="error in inform.note.errors">{{ error }}; </span>
                </div>
            </div>
        </div>
        
        <div class="relative flex flex-col w-160 gap-0.5">
            <label for="name" :class="['text-sm font-medium text-gray-600']"> 
                    Date & Time
            </label>
            <!-- desc -->
            <p class="absolute top-0 right-0 text-sm font-medium text-red-500 pl-0.5">Required</p>
            <!-- Date -->
            <div class="flex gap-4">
                <div :class="['relative w-1/2']">
                    <button @click="nextTick(() => inputEmail.focus())" 
                        class="absolute inset-y-0 items-center left-3">
                        <IconCalendar class="w-5 h-5" />
                    </button>
                    <input type="date" 
                        :min="dayjs().format('YYYY-MM-DD')" :max="dayjs().add('3', 'M').format('YYYY-MM-DD')"
                        @change="inform.datetime.checkError"
                        v-model="inform.datetime.date" 
                        :class="[inform.datetime.isError() ? 'form-input-error' : 'form-input', 'pl-10']">
                </div>
                <div :class="['relative w-1/2']">
                    <button @click="nextTick(() => inputEmail.focus())" 
                        class="absolute inset-y-0 items-center left-3">
                        <IconTime class="w-5 h-5" />
                    </button>
                    <input type="time"
                        @change="inform.datetime.checkError"
                        v-model="inform.datetime.time" 
                        :class="[inform.datetime.isError() ? 'form-input-error' : 'form-input', 'pl-10']">
                </div>
                <div v-show="inform.datetime.isError()" class="absolute -bottom-5 text-sm font-medium text-red-500 pl-0.5">
                    Invalid note error: 
                    <span v-for="error in inform.datetime.errors">{{ error }}; </span>
                </div>
            </div>
        </div>
    </div>

    <!-- button -->
    <div class="flex gap-8">
        <button 
            @click="$emit('emitBack')"
            :class="['btn-primary mt-6 flex items-center gap-2',
            'disabled:bg-gray-300 disabled:text-slate-500']" >
            <IconArrowLeft class="w-5 h-5" />
            <span>Back</span>
        </button>
        <button 
            :disabled="!inform.status"
            @click="inform.send" 
            :class="['btn-primary mt-6', 
            'disabled:bg-gray-300 disabled:text-slate-500']">Submit</button>
    </div>

</div>
</template>
 
<style scoped>
.input-container input {
    border: none;
    box-sizing: border-box;
    outline: 0;
    padding: .75rem;
    position: relative;
    width: 100%;
}

input[type="date"]::-webkit-calendar-picker-indicator {
    background: transparent;
    bottom: 0;
    color: transparent;
    cursor: pointer;
    height: auto;
    left: 0;
    position: absolute;
    right: 0;
    top: 0;
    width: auto;
}
input[type="time"]::-webkit-calendar-picker-indicator {
    background: transparent;
    bottom: 0;
    color: transparent;
    cursor: pointer;
    height: auto;
    left: 0;
    position: absolute;
    right: 0;
    top: 0;
    width: auto;
}
</style>