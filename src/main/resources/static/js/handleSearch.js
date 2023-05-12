const searchField = document.querySelector(".app-main_search-input")
const rowList = document.querySelectorAll(".app-main_table-data-row")
const btnRefresh = document.querySelector(".btn_refresh")
searchField.oninput = ()=>{
    var keyword = searchField.value.toLowerCase()
    rowList.forEach((row)=>{
        if((row.childNodes[3].textContent.toLowerCase()).includes(keyword)){
            if(row.classList.contains("disabled"))row.classList.remove("disabled")
        }else {
            if(!row.classList.contains("disabled"))row.classList.add("disabled")
        }
    })
}


btnRefresh.onclick =()=>{
    searchField.value = ""
    rowList.forEach((row)=>{
        if(row.classList.contains("disabled")){
            row.classList.remove("disabled")
        }
    })
}

