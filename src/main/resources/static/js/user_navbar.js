const btnUser_close = document.querySelector('.navbar_user-close');
const btnAvatar = document.querySelector('.header_option-user');
const navbarUser = document.querySelector('.navbar_user');
const searchInput = document.querySelector('.header_search-input');
const searchBtn = document.querySelector('.header_search-btn');
btnAvatar.onclick = (e)=>{
    navbarUser.style.animation = `SlideInLeft ease .3s forwards`
}
btnUser_close.onclick = (e)=>{
    navbarUser.style.animation = `SlideOut linear .3s forwards`
}
searchInput.addEventListener('keyup', function (e) {
    if (e.key === "Enter") {
        window.location.replace("http://localhost:8080/search?keyword=" + searchInput.value);
    }
})

searchBtn.onclick = (e) => {
    window.location.replace("http://localhost:8080/search?keyword=" + searchInput.value);
}