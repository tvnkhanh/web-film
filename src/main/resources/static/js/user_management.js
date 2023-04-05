const btnStatusList = document.querySelectorAll(".data-option-status")
const app = document.querySelector(".app-management")

btnStatusList.forEach((btnStatus)=>{
    btnStatus.onclick = (e)=>{
        let row = btnStatus.closest(".app-main_table-data-row")
        let username = row.childNodes[7].textContent
        const dialog = document.createElement("div")
        dialog.classList.add("modal")
        if(btnStatus.classList.contains("data-option-status--active")){
            dialog.innerHTML = `
            <div class="modal__overlay"></div>
            <div class="modal__body">
                <form class="app-dialog" id="user-form" action ="/management/User/status/${username}" method="post">
                    <div class="app-dialog_main">
                        <div class="app-dialog_header">
                            <span>Xác nhận</span>
                            <div class="btn_close">
                                <i class="fa-solid fa-close"></i>
                            </div>
                        </div >
                        
                        <div class="app-dialog_container">
                            <div class="app-dialog_group">
                                Bạn có thực sự muốn khóa tài khoản <strong>${username}</strong> không?
                            </div>
                        </div>
                    </div>
                    <button class="btn_add">Xác nhận</button>
                </form>
            </div>
        `;
        }else{
            dialog.innerHTML = `
            <div class="modal__overlay"></div>
            <div class="modal__body">
                <form class="app-dialog" id="user-form" action ="/management/User/status/${username}" method="post">
                    <div class="app-dialog_main">
                        <div class="app-dialog_header">
                            <span>Xác nhận</span>
                            <div class="btn_close">
                                <i class="fa-solid fa-close"></i>
                            </div>
                        </div >
                        
                        <div class="app-dialog_container">
                            <div class="app-dialog_group">
                                Bạn có thực sự muốn mở khóa tài khoản <strong>${username}</strong> không?
                            </div>
                        </div>
                    </div>
                    <button class="btn_add">Xác nhận</button>
                </form>
            </div>
        `;
        }
        app.appendChild(dialog);
        var btnClose = document.querySelector(".btn_close")
        btnClose.onclick = () => {
            var dialog = document.querySelector(".modal")
            app.removeChild(dialog);
        }
    }
})