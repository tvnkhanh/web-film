const btnAdd = document.querySelector(".app-main_btn_add");
const btnEditList = document.querySelectorAll(".data-option-edit")
const btnDeleteList = document.querySelectorAll('.data-option-delete')
const app = document.querySelector(".app-management")
btnAdd.onclick = () => {
    const dialog = document.createElement("div")
    dialog.classList.add("modal")
    dialog.innerHTML = `
    <div class="modal__overlay">
    </div>
    <div class="modal__body">
        <form class="app-dialog" id="film_category-form" th:action ="@{management/filmCategory/add}" method="post">
            <div class="app-dialog_main">
                <div class="app-dialog_header">
                    <span>Thêm thể loại</span>
                    <div class="btn_close">
                        <i class="fa-solid fa-close"></i>
                    </div>
                </div >
                
                <div class="app-dialog_container">
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <input id="name" name ="name" type="text" placeholder="Tên thể loại"
                                class="app-dialog_item-input">
                            <span class="input_message"></span>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn_add">Thêm</button>
        </form>
    </div>
    `


    var validation = document.createElement("script")
    validation.innerHTML = ` 
    Validator({
        form: "#film_category-form",
        formGroupSelector: '.app-dialog_group',
        errorSelector: '.input_message',
        rules: [
            Validator.isRequired("#name"),
        ]
    });
    `
    app.appendChild(dialog);
    app.appendChild(validation);
    var btnClose = document.querySelector(".btn_close")
    btnClose.onclick = () => {
        var dialog = document.querySelector(".modal")
        app.removeChild(dialog);
        app.removeChild(validation)
    }
}
btnEditList.forEach((btnEdit) => {
    var row = btnEdit.closest('.app-main_table-data-row')
    var idVal  = row.querySelector('.obj_id').id
    var categoryName = row.childNodes[3].textContent
    btnEdit.onclick = () => {
        const dialog = document.createElement("div")
        dialog.classList.add("modal")
        dialog.innerHTML = `
    <div class="modal__overlay">
    </div>
    <div class="modal__body">
        <form class="app-dialog" id="film_category-form" th:action ="@{management/filmCategory/edit/${idVal}}" method="post">
            <div class="app-dialog_main">
                <div class="app-dialog_header">
                    <span>Cập nhật thông tin thể loại</span>
                    <div class="btn_close">
                        <i class="fa-solid fa-close"></i>
                    </div>
                </div >
                
                <div class="app-dialog_container">
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <input id="name" name = "name" type="text" placeholder="Tên thể loại"
                               value="${categoryName}" class="app-dialog_item-input">
                            <span class="input_message"></span>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn_add">Cập nhật</button>
        </form>
    </div>
    `;
        var validation = document.createElement("script")
        validation.innerHTML = ` 
    Validator({
        form: "#film_category-form",
        formGroupSelector: '.app-dialog_group',
        errorSelector: '.input_message',
        rules: [
            Validator.isRequired("#name"),
        ]
    });
    `
        app.appendChild(dialog);
        app.appendChild(validation);
        var btnClose = document.querySelector(".btn_close")
        btnClose.onclick = () => {
            var dialog = document.querySelector(".modal")
            app.removeChild(dialog);
            app.removeChild(validation)
        }
    }
}
)

btnDeleteList.forEach((btnDelete) => {
    var row = btnDelete.closest('.app-main_table-data-row')
    var idVal  = row.querySelector('.obj_id').id
    var categoryName = row.childNodes[3].textContent
    btnDelete.onclick = () => {
        const dialog = document.createElement("div")
        dialog.classList.add("modal")
        dialog.innerHTML = `
            <div class="modal__overlay"></div>
            <div class="modal__body">
                <form class="app-dialog" id="film_category-form" th:action ="@{/management/filmCategory/delete/${idVal}}" >
                    <div class="app-dialog_main">
                        <div class="app-dialog_header">
                            <span>Xác nhận</span>
                            <div class="btn_close">
                                <i class="fa-solid fa-close"></i>
                            </div>
                        </div >
                        
                        <div class="app-dialog_container">
                            <div class="app-dialog_group">
                                Bạn có thực sự muốn xóa thể loại <strong>${categoryName}</strong> không?
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