function showMessage(message){
    const body = document.querySelector("body")
    var messageform = document.createElement("div")
    messageform.classList.add("message")
    messageform.innerHTML=`
        <div class="message_header">
            <span>Thông báo</span>
            <i class="message_icon-close fa-solid fa-xmark"></i>
        </div>
        <div class="message_body">
                <span class="message_msg">
                    ${message}
                </span>
        </div>
        <div class="btn_close-message">Đã hiểu</div>
        `
    body.appendChild(messageform)
    var btnClose = document.querySelector(".btn_close-message")
    var iconClose = document.querySelector(".message_icon-close")
    btnClose.onclick = ()=>{
        body.removeChild(messageform)
    }
    iconClose.onclick = ()=>{
        body.removeChild(messageform)
    }
    // }
}
function loadCategory(categoryList) {
    var s =  categoryList.reduce((output,category )=>{
        return output + ',' + category.categoryName;
    } )
    const category = document.querySelector(".categoryStr")
    category.innerText = s;
}