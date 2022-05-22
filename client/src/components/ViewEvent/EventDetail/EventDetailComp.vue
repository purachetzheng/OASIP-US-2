<script setup>
import { ref, computed, onBeforeMount, onMounted, reactive, toRef, } from 'vue'
import dayjs from 'dayjs'
import IconCalendar from '../../icons/IconCalendar.vue';
import IconEmail from '../../icons/IconEmail.vue';
import IconTime from '../../icons/IconTime.vue';
import IconClose from '../../icons/IconClose.vue';
import IconSave from '../../icons/IconSave.vue';
defineEmits(['emitSave', 'emitCancel'])
const props = defineProps({
    event: {
        type: Object,
        require: true,
    },
    editingMode:{
        type: Boolean,
        require: true
    }
})

onBeforeMount( async() => {
    
    editing.setup()
})
const editingEvent = ref({
    date: null, time: null, note: null
})

const editing = reactive({
    event: { date: null, time: null, note: null},
    setup(){
        console.log(props.event);
        console.log(dayjs(props.event.eventStartTime).format('YYYY-MM-DD'));
        this.event = {
            date: dayjs(props.event.eventStartTime).format('YYYY-MM-DD'),
            time: dayjs(props.event.eventStartTime).format('HH:mm'),
            note: props.event.eventNotes
        }
            console.log(editing);
    }
    
})

</script>
 
<template>
    <div class="flex flex-col h-full w-3/4 px-6 border-r-2 overflow-auto">
        <!-- Name -->
        <div class="my-6">
            <p class="text-3xl font-medium truncate">{{ event.bookingName }}</p>
        </div>
        <div class="flex flex-col gap-2">
            <!-- Email -->
            <div class="flex gap-4 items-center">
                <IconEmail class="w-6 h-6" />
                <span class="text-lg font-medium">{{ event.bookingEmail }}</span>
            </div>
            <!-- Date -->
            <div class="flex gap-4 items-center">
                <IconCalendar class="w-6 h-6" />
                <input v-if="editingMode" type="date" name="" id=""
                    class="text-lg font-medium w-fit bg-gray-200 rounded-md px-2" v-model="editing.event.date">
                <span v-else class="text-lg font-medium">
                    {{ dayjs(event.eventStartTime).format('LL') }}
                </span>
            </div>
            <!-- Time -->
            <div class="flex gap-4 items-center">
                <IconTime class="w-6 h-6" />
                <input v-if="editingMode" type="time" name="" id=""
                    class="text-lg font-medium w-fit bg-gray-200 rounded-md px-2" v-model="editing.event.time">
                <span v-else class="text-lg font-medium">
                    {{ dayjs(event.eventStartTime).format('LT') }} -
                    {{ dayjs(event.eventStartTime).add(event.eventDuration, 'm').format('LT') }}
                </span>

            </div>

        </div>
        <!-- Note -->
        <div class="flex h-full w-full bg-white my-6">
            <textarea v-if="editingMode" name="eventNotes" placeholder="Note" maxlength="500"
                class="w-full border p-1 text-md font-medium bg-gray-200" @change=""
                v-model="editing.event.note"></textarea>
            <div v-else class="">
                <span class="text-md">{{ event.eventNotes }}</span>
                <span v-show="event.eventNotes == null || event.eventNotes.length == 0"
                    class="text-md font-medium text-gray-500">No Description</span>
            </div>

        </div>
        <!-- Show when editing mode on -->
        <div v-if="editingMode" class="flex mb-6 gap-4">
            <button type="button" @click="$emit('emitSave')" 
                class="text-sm font-medium tracking-wide px-3 py-2 rounded-full bg-blue-500 text-gray-100
                                    flex items-center gap-1">
                <IconSave class="w-6 h-6" />
                <span class="hidden md:block">Save</span>
            </button>
            <button type="button" @click="$emit('emitCancel')" 
                class="text-sm font-medium tracking-wide px-3 py-2 rounded-full text-gray-800 hover:text-gray-100 hover:bg-red-500  
                                    flex items-center gap-1">
                <IconClose class="w-6 h-6 md:-ml-1.5" />
                <span class="hidden md:block">Cancel</span>
            </button>
        </div>
    </div>
</template>
 
<style>
</style>