const btnUser_close = document.querySelector('.navbar_user-close');
const btnAvatar = document.querySelector('.header_option-user');
const navbarUser = document.querySelector('.navbar_user');
const navUser_Tab_item = document.querySelectorAll('.navbar_user-header-tab-item');
const btnNotification = document.querySelector('.header_option_notification');
btnAvatar.onclick = (e)=>{
    // navbarUser.classList.add('navbar_user--show');
    // navbarUser.classList.remove('.navbar_user--hidden')
    navUser_Tab_item[0].classList.add('navbar_user-header-tab--active')
    navUser_Tab_item[1].classList.remove('navbar_user-header-tab--active')
    navbarUser.style.animation = `SlideInLeft ease .3s forwards`
}
btnUser_close.onclick = (e)=>{
    navbarUser.style.animation = `SlideOut linear .3s forwards`
}
// btnAvatar.onclick =(e)=>{
//     console.log("click")
// }

btnNotification.onclick =(e)=>{
    navUser_Tab_item[1].classList.add('navbar_user-header-tab--active')
    navUser_Tab_item[0].classList.remove('navbar_user-header-tab--active')
    navbarUser.style.animation = `SlideInLeft ease .3s forwards`
}