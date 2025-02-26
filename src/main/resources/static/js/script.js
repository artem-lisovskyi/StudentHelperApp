$(document).ready(function() {
    $('.slider').slick({
        arrows: true,
        slidesToShow: 13,
        autoplay: true,
        speed: 1000,
        autoplaySpeed: 800,
        responsive: [{
                breakpoint: 768,
                settings: {
                    slidesToShow: 2
                }
            },
            {
                breakpoint: 550,
                settings: {
                    slidesToShow: 1
                }
            }
        ]
    });
});