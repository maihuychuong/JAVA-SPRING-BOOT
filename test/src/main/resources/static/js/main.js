document.addEventListener("DOMContentLoaded", function () {
    let index = 0;
    const banners = document.querySelectorAll(".banner-img");

    function showBanner() {
        banners.forEach((img, i) => {
            img.classList.remove("active");
            if (i === index) img.classList.add("active");
        });

        index = (index + 1) % banners.length;
    }

    showBanner();
    setInterval(showBanner, 3000); // Chuyển ảnh sau 3 giây
});
