document.addEventListener('DOMContentLoaded', function(){
var swipper = new Swiper('.swiper-container',{


    slidesPerView: 2,
    spaceBetween: 20,
    loop: true,
    navigation: {

        nextE1: '.swiper-button-next',
        prevE1: '.swiper-button-prev',
    },

    autoplay: {
        delay: 5000,
    },

    

});


});

