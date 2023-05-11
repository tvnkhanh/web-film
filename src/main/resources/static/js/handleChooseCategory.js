
const categoryList = document.querySelector(".category_list")
const categoryChoosedList = document.querySelector(".category_choosed")
const categoryItemList = categoryList.querySelectorAll(".category_item")
const categoryChoosedItemList = categoryChoosedList.querySelectorAll(".category_item")
const form = document.querySelector("#choose-form")

categoryItemList.forEach((categoryItem)=>{
    categoryItem.onclick = ()=>{
        var categoryInput = categoryItem.querySelector(".category_input")
        var idCategory = categoryInput.id.split("_")[1].trim()
        categoryItem.classList.add("disabled")
        categoryChoosedItemList.forEach((itemChoosed)=>{
            var itemChoosedId = itemChoosed.querySelector(".category_input--choosed").id
            if(itemChoosedId == idCategory){
                itemChoosed.classList.remove("disabled")
                return;
            }
        })

    }
})

categoryChoosedItemList.forEach((categoryChoosedItem)=>{
    categoryChoosedItem.querySelector(".icon_remove").onclick = ()=>{
        var itemChoosedId = categoryChoosedItem.querySelector(".category_input--choosed").id
        categoryChoosedItem.classList.add("disabled")
        categoryItemList.forEach((item)=>{
            var itemId = item.querySelector(".category_input").id.split("_")[1].trim()
            if(itemChoosedId == itemId){
                item.classList.remove("disabled")
                return;
            }
        })
    }
})

form.onsubmit = (e)=>{
    e.preventDefault()
    var status =false
    categoryChoosedItemList.forEach((categoryChoosedItem)=>{
        if(!categoryChoosedItem.classList.contains("disabled")){
            status = true;
            return;
        }
        
    })
    if(status){
        categoryChoosedItemList.forEach((categoryChoosedItem)=>{
            if(categoryChoosedItem.classList.contains("disabled")){
                categoryChoosedList.removeChild(categoryChoosedItem)
            }else{
                var itemInput = categoryChoosedItem.querySelector(".category_input--choosed")
                itemInput.value = itemInput.id
            }

        })
        form.submit()
    }else {
        showMessage("Vui lòng chọn tối thiểu 1 thể loại trước khi nhấn nút hoàn tất!")
    }
}

