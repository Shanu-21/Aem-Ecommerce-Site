document.addEventListener('DOMContentLoaded', function(){
var swipper = new Swiper('.swiper-container',{


    slidesPerView: 3,
    spaceBetween: 20,
    loop: true,
    navigation: {

        nextE1: '.swiper-button-next',
        prevE1: '.swiper-button-prev',
    },

    autoplay: {
        delay: 5000,
    },

    breakpoints : {

        640: {slidesPerView: 1},
        768: {slidesPerView: 2},
        1024: {slidesPerView: 3},
    },
    

});


});

