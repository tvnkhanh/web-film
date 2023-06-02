function handlePagination(valuePage, type){
    const {totalPage, currentPage} = valuePage
    const pagination = document.querySelector(".pagination")
    var backward = document.querySelector(".pagination-item .fa-backward-step").closest(".pagination-item")
            backward.classList.add("pagination-item")
            if(1 == currentPage){
                backward.classList.add("activated")
            }
            var href =''
            if(type == 'anime'){
                href = getcurrentPathNameForAnimePage()
            }else{
                href=getcurrentPathNameForMoviePage()
            }
    backward.querySelector(".pagination-link").href =href+"1"
    var maxLeft = currentPage - 2
    var maxRight = currentPage + 2
    if(maxLeft<=0){
        maxLeft = 1   
        maxRight = 5
    }
    if(maxRight>totalPage){
        maxRight = totalPage
        maxLeft = currentPage - ( 5 - (totalPage-currentPage+1))
        if(maxLeft<=0){
            maxLeft = 1   
        }
    }
        for(let i = maxLeft; i<=maxRight; i++){
            var pageitem = document.createElement("li")
            pageitem.classList.add("pagination-item")
            if(i == currentPage){
                pageitem.classList.add("activated")
            }
            pageitem.innerHTML =`
                <a href="${href+i}" class="pagination-link">
                            ${i}
                </a>
            `
            pagination.appendChild(pageitem)
        }
    var forward = document.createElement("li")
            forward.classList.add("pagination-item")
            if(totalPage == currentPage){
                forward.classList.add("activated")
            }
            forward.innerHTML =`
            <a href="${href+totalPage}" class="pagination-link">
                <i class="pagination-icon fa-sharp fa-solid fa-forward-step"></i>
            </a>
            `
    pagination.appendChild(forward)
    function getcurrentPathNameForAnimePage(){
        const pathName = window.location.pathname;
        const search = window.location.search;
        let href = "";
        if(pathName.includes("/anime/page-")){
            href = "/anime/page-"
        }else if(pathName.includes("/anime/filter")){
            href = "/anime/filter"
            var temp = search.split("&")
            href+=temp[0]+"&page="
        }else {
            var temp = pathName.split("/")
            href = "/"+temp[1]+"/"+temp[2]+"/page-"
        }
        return href;
    }
    function getcurrentPathNameForMoviePage(){
        return "/movie/page-";
    }
}
