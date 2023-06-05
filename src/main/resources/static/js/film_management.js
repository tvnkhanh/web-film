const btnDeleteList = document.querySelectorAll(".data-option-delete")
const btnAdd = document.querySelector(".app-main_btn_add")
const app =  document.querySelector(".app-management")

btnAdd.onclick = ()=>{
    const dialog = document.createElement("div")
    dialog.classList.add("modal")
    dialog.innerHTML = `
    <div class="modal__overlay">
    </div>
    <div class="modal__body">
        <form class="app-dialog" id="film-form" action ="/management/Film/Add"  method="post">
            <div class="app-dialog_main">
                <div class="app-dialog_header">
                    <span>Thêm phim</span>
                    <div class="btn_close">
                        <i class="fa-solid fa-close"></i>
                    </div>
                </div >
                
                <div class="app-dialog_container">
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <input id="name" name ="name" type="text" placeholder="Tên phim"
                                class="app-dialog_item-input">
                            <span class="input_message"></span>
                        </div>
                        
                    </div>
                    <div class="app-dialog_group">
                        
                        <div class="app-dialog_item">
                            <input id="thumb" name ="thumb" type="text" placeholder="Link ảnh 1(Dọc)"
                                class="app-dialog_item-input">
                            <span class="input_message"></span>
                        </div>
                        
                        <div class="img_preview" style = "display: flex; justify-content: center;margin: 2px auto;">
                                
                        </div>
                    </div>
                    <div class="app-dialog_group">
                        
                        <div class="app-dialog_item">
                            <input id="thumb2" name ="thumb2" type="text" placeholder="Link ảnh 2(Ngang)"
                                class="app-dialog_item-input" >
                            <span class="input_message"></span>
                        </div>
                        <div class="img_preview" style = "display: flex; justify-content: center;margin: 2px auto;">
                                
                        </div>
                    </div>
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <input id="description" name ="description" type="text" placeholder="Mô tả"
                                class="app-dialog_item-input" >
                            <span class="input_message"></span>
                        </div>
                    </div>
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <input id="price" name ="price" type="text" placeholder="Giá(Mặc định là 0 FPoint)"
                                class="app-dialog_item-input" >
                            <span class="input_message"></span>
                        </div>
                    </div>
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <select name="charge" id="charge" class="app-dialog_item-input">
                                <option value="MOVIE">MOVIE</option>
                                <option value="TV-SERIES">TV-SERIES</option>
                            </select>
                            <span class="input_message"></span>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn_add">Thêm</button>
        </form>
    </div>

    `;

    // <input type="text" name="charge" list="chargeList" class="app-dialog_item-input" placeholder="Phim có phí?">
    //                             <datalist id="chargeList">
    //                                 <option value="No">
    //                                 <option value="Yes">
    //                             </datalist>
    var validation = document.createElement("script")
    validation.innerHTML = ` 
    Validator({
        form: "#film-form",
        formGroupSelector: '.app-dialog_group',
        errorSelector: '.input_message',
        rules: [
            Validator.isRequired("#name"),
            Validator.isRequired("#thumb"),
            Validator.isRequired("#thumb2")
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
    var thumb = dialog.querySelector("#thumb")
    thumb.oninput = ()=>{
        console.log("thumb focus lost")
        var temp = thumb.value
        if(temp!=""){
            var imgPreview = thumb.closest(".app-dialog_group").querySelector(".img_preview")
            imgPreview.innerHTML =`<img src="${temp}" alt="" style="object-fit: cover; height: 226px; width: 160px; border-radius :3px; ">`
        }

    }
    var thumbbxh = dialog.querySelector("#thumb2")
    thumbbxh.oninput = ()=>{
        console.log("thumb focus lost")
        var temp = thumb2.value
        if(temp!=""){
            var imgPreview = thumbbxh.closest(".app-dialog_group").querySelector(".img_preview")
            imgPreview.innerHTML =`<img src="${temp}" alt="" style="object-fit: cover; height: 128px; width: 240px; border-radius :3px; ">`
        }

    }
}
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
                <form class="app-dialog" id="film-form" action ="/management/Film/Delete/${idVal}" method="post">
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