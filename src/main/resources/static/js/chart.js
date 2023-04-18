// const charData = {
//     labels: ['30 day', '60 day', '90 day', '120 day', 'Other'],
//     data:[40, 10, 30, 5, 15]
//
// }
// const myChart = document.querySelector(".doughnut-chart")
//
// new Chart(myChart,{
//     type: "doughnut",
//     data: {
//         labels:charData.labels,
//         datasets: [
//             {
//                 label: "Pack ratio:",
//                 data: charData.data
//             }
//         ]
//     },
//     options: {
//         borderWidth: 10,
//         borderRadius: 2,
//         hoverBorderWidth: 2,
//         // plugins: {
//         //     legend: {
//         //         display: true
//         //     }
//         // }
//     }
// })

// event
// var yearInput = document.querySelector("#year .statistic_revenue-item-value")
// console.log(yearInput)
// yearInput.attributes({
//     "min":2010,
//     "max":new Date().getFullYear()
// })
// yearInput.ariaValueMin= 2010;
// yearInput.ariaValueMax= new Date().getFullYear();

const choice = document.querySelector(".statistic_revenue-choice")
choice.onchange = ()=>{
    console.log(choice.value)
    var dayInput = document.getElementById("day")
    var monthInput = document.getElementById("month")
    var yearInput = document.querySelector("#year")
    if(choice.value == 'day'){
        
        if(dayInput.classList.contains('disabled')){
            dayInput.classList.remove('disabled')
        }
        if(!monthInput.classList.contains('disabled')){
            monthInput.classList.add('disabled')
        }
        if(!yearInput.classList.contains('disabled')){
            yearInput.classList.add('disabled')
        }
    }
    if(choice.value == 'month'){
        if(!dayInput.classList.contains('disabled')){
            dayInput.classList.add('disabled')
        }
        if(monthInput.classList.contains('disabled')){
            monthInput.classList.remove('disabled')
        }
        if(yearInput.classList.contains('disabled')){
            yearInput.classList.remove('disabled')
        }
    }
    if(choice.value == 'year'){
        if(!dayInput.classList.contains('disabled')){
            dayInput.classList.add('disabled')
        }
        if(!monthInput.classList.contains('disabled')){
            monthInput.classList.add('disabled')
        }
        if(yearInput.classList.contains('disabled')){
            yearInput.classList.remove('disabled')
        }
    }
}