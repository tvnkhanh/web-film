// const btnAdd = document.querySelector(".app-main_btn_add")
const btnEdit = document.querySelector(".app-main_btn_edit")
const btnDelete = document.querySelector(".app-main_btn_delete")
const btnAddEps = document.querySelector(".app-main_btn_add-eps")
const btnEditEpsList = document.querySelectorAll(".data-option-edit")
const btnDeleteEpsList = document.querySelectorAll(".data-option-delete")
const app =  document.querySelector(".app-management")
const idFilm = document.querySelector(".id-film").id.split("_")[1]
console.log(idFilm)


//Espisode
btnAddEps.onclick = (e) =>{
    const dialog = document.createElement("div")
    dialog.classList.add("modal")
    dialog.innerHTML = `
    <div class="modal__overlay">
    </div>
    <div class="modal__body">
        <form class="app-dialog" id="film-form" action ="/management/Film/AddEps/${idFilm}" method="post">
            <div class="app-dialog_main">
                <div class="app-dialog_header">
                    <span>Thêm tập</span>
                    <div class="btn_close">
                        <i class="fa-solid fa-close"></i>
                    </div>
                </div >
                
                <div class="app-dialog_container">
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <input id="name" name ="name" type="text" placeholder="Tên tập"
                                class="app-dialog_item-input">
                            <span class="input_message"></span>
                        </div>
                        
                    </div>
                    <div class="app-dialog_group">
                        
                        <div class="app-dialog_item">
                            <input id="thumb" name ="thumb" type="text" placeholder="Link ảnh"
                                class="app-dialog_item-input">
                            <span class="input_message"></span>
                        </div>
                        <div class="img_preview" style = "display: flex; justify-content: center;margin: 2px auto;">
                               
                         </div>
                    </div>
                    <div class="app-dialog_group">
                        
                        <div class="app-dialog_item">
                            <input id="video" name ="video" type="text" placeholder="Link video"
                                class="app-dialog_item-input">
                            <span class="input_message"></span>
                        </div>
                        <div class="video_preview" style = "border-radius : 3px; margin: 2px auto; display: flex; justify-content: center;">
                               
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn_add">Thêm</button>
        </form>
    </div>

    `;
    var validation = document.createElement("script")
    validation.innerHTML = ` 
    Validator({
        form: "#film-form",
        formGroupSelector: '.app-dialog_group',
        errorSelector: '.input_message',
        rules: [
            Validator.isRequired("#name"),
            Validator.isRequired("#thumb"),
            Validator.isRequired("#video")
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
    var video = dialog.querySelector("#video")
    thumb.oninput = ()=>{
        console.log("thumb focus lost")
        var temp = thumb.value
        if(temp!=""){
            var imgPreview = dialog.querySelector(".img_preview")
            imgPreview.innerHTML =`<img src="${temp}" alt="" style="object-fit: cover; height: 180px; width: 240px; border-radius :3px; ">`
        }

    }
    video.oninput = ()=>{
        var temp = video.value
        if(temp!=""){
            var videoPreview = dialog.querySelector(".video_preview")
            videoPreview.innerHTML =`<iframe width="560" height="315" src="https://www.youtube.com/embed/${temp}" 
            title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media;
             gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>`
        }

    }
    
}

btnEditEpsList.forEach((btnEditEps)=> {
    btnEditEps.onclick = (e) =>{
        var row = btnEditEps.closest('.app-main_table-data-row')
        var idEps  = row.querySelector('.obj_id').id.split("_")[1];
        var epsName = row.childNodes[3].textContent
        var epsImg = row.childNodes[9].textContent
        var epsVid = row.childNodes[11].textContent
        const dialog = document.createElement("div")
        dialog.classList.add("modal")
        dialog.innerHTML = `
        <div class="modal__overlay">
        </div>
        <div class="modal__body">
            <form class="app-dialog" id="film-form" action ="/management/Film/EditEps/${idFilm}/${idEps}"  method="post">
                <div class="app-dialog_main">
                    <div class="app-dialog_header">
                        <span>Sửa thông tin tập</span>
                        <div class="btn_close">
                            <i class="fa-solid fa-close"></i>
                        </div>
                    </div >
                    
                    <div class="app-dialog_container">
                        <div class="app-dialog_group">
                            <div class="app-dialog_item">
                                <input id="name" name ="name" type="text" placeholder="Tên tập"
                                    class="app-dialog_item-input" value = ${epsName}>
                                <span class="input_message"></span>
                            </div>
                            
                        </div>
                        <div class="app-dialog_group">
                            
                            <div class="app-dialog_item">
                                <input id="thumb" name ="thumb" type="text" placeholder="Link ảnh"
                                    class="app-dialog_item-input" value = ${epsImg}>
                                <span class="input_message"></span>
                            </div>
                            <div class="img_preview" style = "display: flex; justify-content: center;margin: 2px auto;">
                                <img src="${epsImg}" alt="" style="object-fit: cover; height: 180px; width: 240px; border-radius :3px; ">
                            </div>
                        </div>
                        <div class="app-dialog_group">
                            <div class="app-dialog_item">
                                <input id="video" name ="video" type="text" placeholder="Link video"
                                    class="app-dialog_item-input" value = ${epsVid}>
                                <span class="input_message"></span>
                            </div>
                            
                            <div class="video_preview" style = "border-radius : 3px; margin: 2px auto; display: flex; justify-content: center;">
                                <iframe width="380" height="240" src="https://www.youtube.com/embed/${epsVid}" title="YouTube video player" 
                                frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
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
            form: "#film-form",
            formGroupSelector: '.app-dialog_group',
            errorSelector: '.input_message',
            rules: [
                Validator.isRequired("#name"),
                Validator.isRequired("#thumb"),
                Validator.isRequired("#video")
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
        var video = dialog.querySelector("#video")
        thumb.oninput = ()=>{
            console.log("thumb focus lost")
            var temp = thumb.value
            if(temp!=""){
                var imgPreview = dialog.querySelector(".img_preview")
                imgPreview.innerHTML =`<img src="${temp}" alt="" style="object-fit: cover; height: 180px; width: 240px; border-radius :3px; ">`
            }

        }
        video.oninput = ()=>{
            var temp = video.value
            if(temp!=""){
                var videoPreview = dialog.querySelector(".video_preview")
                videoPreview.innerHTML =`<iframe width="560" height="315" src="https://www.youtube.com/embed/${temp}" 
            title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media;
             gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>`
            }

        }
    }
}) 


btnDeleteEpsList.forEach((btnDeleteEps)=> {
    btnDeleteEps.onclick = (e) =>{
        var row = btnDeleteEps.closest('.app-main_table-data-row')
        var idEps  = row.querySelector('.obj_id').id.split("_")[1];
        var epsName = row.childNodes[3].textContent
        const dialog = document.createElement("div")
        dialog.classList.add("modal")
        dialog.innerHTML = `
        <div class="modal__overlay">
        </div>
        <div class="modal__body">
            <form class="app-dialog" id="film-form" action ="/management/Film/DeleteEps/${idFilm}/${idEps}"  method="post">
                <div class="app-dialog_main">
                    <div class="app-dialog_header">
                        <span>Xóa tập</span>
                        <div class="btn_close">
                            <i class="fa-solid fa-close"></i>
                        </div>
                    </div >
                    
                    <div class="app-dialog_container">
                        <div class="app-dialog_group">
                            Bạn có thực sự muốn xóa tập <strong>${epsName}</strong> không?
                        </div>
                    </div>
                </div>
                <button class="btn_add">Xóa</button>
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
}) 


//Film
btnEdit.onclick = (e) =>{
    const dialog = document.createElement("div")
    dialog.classList.add("modal")
    var filmName = document.querySelector(".film_detail-info-name").textContent.trim() || document.querySelector(".film_detail-info-name").innerText.trim();
    console.log(filmName)
    var filmImg = document.querySelector(".film_detail-thumb").src
    var filmImg2 = document.querySelector(".film_detail-thumb2").src
    var filmDes = document.querySelector(".film_detail-info-description").textContent.trim() || document.querySelector(".film_detail-info-description").innerText.trim();
    var filmType = document.querySelector(".film_type").textContent.trim()
    dialog.innerHTML = `
    <div class="modal__overlay">
    </div>
    <div class="modal__body">
        <form class="app-dialog" id="film-form" action ="/management/Film/Edit/${idFilm}"  method="post">
            <div class="app-dialog_main">
                <div class="app-dialog_header">
                    <span>Sửa thông tin phim</span>
                    <div class="btn_close">
                        <i class="fa-solid fa-close"></i>
                    </div>
                </div >
                
                <div class="app-dialog_container">
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <input id="name" name ="name" type="text" placeholder="Tên phim"
                                class="app-dialog_item-input" value = "${filmName}">
                            <span class="input_message"></span>
                        </div>
                        
                    </div>
                    <div class="app-dialog_group">
                        
                        <div class="app-dialog_item">
                            <input id="thumb" name ="thumb" type="text" placeholder="Link ảnh 1(Dọc)"
                                class="app-dialog_item-input" value = "${filmImg}">
                            <span class="input_message"></span>
                        </div>
                        
                        <div class="img_preview" style = "display: flex; justify-content: center;margin: 2px auto;">
                                <img src="${filmImg}" alt="" style="object-fit: cover; height: 200px; width: 160px; border-radius :3px; ">
                        </div>
                    </div>
                    <div class="app-dialog_group">
                        
                        <div class="app-dialog_item">
                            <input id="thumb2" name ="thumb2" type="text" placeholder="Link ảnh 2(Ngang)"
                                class="app-dialog_item-input" value = "${filmImg2}">
                            <span class="input_message"></span>
                        </div>
                        <div class="img_preview" style = "display: flex; justify-content: center;margin: 2px auto;">
                                <img src="${filmImg2}" alt="" style="object-fit: cover; height: 140px; width: 240px; border-radius :3px; ">
                        </div>
                    </div>
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <input id="description" name ="description" type="text" placeholder="Mô tả"
                                class="app-dialog_item-input" value = "${filmDes}">
                            <span class="input_message"></span>
                        </div>
                    </div>
                    <div class="app-dialog_group">
                        <div class="app-dialog_item">
                            <select name="charge" id="charge" class="app-dialog_item-input">
                                <option value="Limit">Limit</option>
                                <option value="Free">Free</option>
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
            Validator.isRequired("#video")
        ]
    });
    `
    var cmbType = dialog.querySelector("#charge")
    console.log(filmType)
    cmbType.value = filmType;
    app.appendChild(dialog);
    app.appendChild(validation);
    var btnClose = document.querySelector(".btn_close")
    btnClose.onclick = () => {
        var dialog = document.querySelector(".modal")
        app.removeChild(dialog);
        app.removeChild(validation)
    }

    
}

    btnDelete.onclick = () => {
        const dialog = document.createElement("div")
        dialog.classList.add("modal")
        var filmName = document.querySelector(".film_detail-info-name").textContent.trim() || document.querySelector(".film_detail-info-name").innerText.trim();
        dialog.innerHTML = `
            <div class="modal__overlay"></div>
            <div class="modal__body">
                <form class="app-dialog" id="film_category-form" action ="/management/Film/Delete/${idFilm}"  method="post">
                    <div class="app-dialog_main">
                        <div class="app-dialog_header">
                            <span>Xác nhận</span>
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