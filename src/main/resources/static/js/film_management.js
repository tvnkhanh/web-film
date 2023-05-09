const btnDeleteList = document.querySelectorAll(".data-option-delete")
btnDeleteList.forEach((btnDelete) => {
        var row = btnDelete.closest('.app-main_table-data-row')
        var idVal  = row.querySelector('.obj_id').id
        var filmName = row.childNodes[3].textContent
        btnDelete.onclick = () => {
            const dialog = document.createElement("div")
            dialog.classList.add("modal")
            dialog.innerHTML = `
            <div class="modal__overlay"></div>
            <div class="modal__body">
                <form class="app-dialog" id="film-form" action ="/management/Film/delete/${idVal}" method="post">
                    <div class="app-dialog_main">
                        <div class="app-dialog_header">
                            <span>Xóa phim</span>
                            <div class="btn_close">
                                <i class="fa-solid fa-close"></i>
                            </div>
                        </div >
                        
                        <div class="app-dialog_container">
                            <div class="app-dialog_group">
                                Bạn có thực sự muốn xóa phim <strong>${filmName}</strong> không?
                            </div>
                        </div>
                    </div>
                    <button class="btn_add">Xác nhận</button>
                </form>
            </div>
        `;
            app.appendChild(dialog);
            var btnClose = document.querySelector(".btn_close")
            btnClose.onclick = () => {
                var dialog = document.querySelector(".modal")
                app.removeChild(dialog);
            }
        }
    }
)