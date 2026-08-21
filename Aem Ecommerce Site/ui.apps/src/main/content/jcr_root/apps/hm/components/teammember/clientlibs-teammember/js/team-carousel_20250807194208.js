document.addEventListener('DOMContentLoaded', function(){
var swipper = new Swipper('.swipper-container',{


    slidesPerView: 3,
    spaceBetween: 20,
    loop: true,
    navigation: {

        nextE1: '.button-next',
        prevE1: '.button-prev',
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

