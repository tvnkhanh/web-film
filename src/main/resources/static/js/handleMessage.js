function showMessage(message){
    const body = document.querySelector("body")

    // btnshow.onclick = ()=>{
    var messageform = document.createElement("div")
    messageform.classList.add("message")

    const autoRemoveId = setTimeout(function() {
        body.removeChild(messageform)
    },3000 +1000)
    messageform.onclick = function(e){
        if(e.target.closest('.btn_close-message')){
            body.removeChild(messageform)
            clearTimeout(autoRemoveId)
        }
        if(e.target.closest('.message_icon-close')){
            body.removeChild(messageform)
            clearTimeout(autoRemoveId)
        }
    }
    messageform.style.animation=`hideMessage linear 3s 3s forwards`
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
    // }
}
