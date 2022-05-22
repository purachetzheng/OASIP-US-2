<script setup>
import { onBeforeMount, reactive } from 'vue'
import dayjs from 'dayjs'
defineEmits([])
const props = defineProps({
    eventStartTime: {
        type: String,
        require: true,
    },
})
onBeforeMount( async() => {
    //first time left trick
    updateTimeleft()
})
const timeleft = reactive({ day: 0, hour: 0, minute: 0, second: 0 })
const updateTimeleft = () => {
    const eventDT = dayjs(props.eventStartTime)
    const nowDT = dayjs()
    timeleft.day = eventDT.diff(nowDT, 'day')
    timeleft.hour = eventDT.diff(nowDT, 'hour') % 24
    timeleft.minute = eventDT.diff(nowDT, 'minute') % 60
    timeleft.second = eventDT.diff(nowDT, 'second') % 60
}
setInterval(updateTimeleft, 500);
</script>
 
<template>
    <div class="flex flex-col bg-white h-2/3 py-4 w-full gap-2 justify-center overflow-hidden">
        <span class="text-lg font-medium text-center">Time Left</span>
        <div class="flex gap-1 w-full justify-center overflow-auto ">
            <!-- day -->
            <div class="flex flex-col items-center bg-blue-50 w-10 py-1.5">
                <div class="text-sm font-medium">Day</div>
                <span class="text-2xl font-medium">
                    {{ timeleft.day }}
                </span>
            </div>

            <div class="flex flex-col items-center bg-blue-50 w-10 py-1.5">
                <div class="text-sm font-medium">Hour</div>
                <span class="text-2xl font-medium">
                    {{ timeleft.hour }}
                </span>
            </div>

            <div class="flex flex-col items-center bg-blue-50 w-10 py-1.5">
                <div class="text-sm font-medium">Min</div>
                <span class="text-2xl font-medium">
                    {{ timeleft.minute }}
                </span>
            </div>

            <div class="flex flex-col items-center bg-blue-50 w-10 py-1.5">
                <div class="text-sm font-medium">Sec</div>
                <span class="text-2xl font-medium">
                    {{ timeleft.second }}
                </span>
            </div>
        </div>
    </div>
</template>
 
<style>
</style>