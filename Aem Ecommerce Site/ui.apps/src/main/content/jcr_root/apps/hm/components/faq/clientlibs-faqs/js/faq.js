document.addEventListener("DOMContentLoaded", function () {
    const faqitems = this.documentElement.querySelectorAll('.faq-question');

    faqitems.forEach(btn => {

        btn.addEventListener("click", () => {

            const answer = btn.nextElementSibling;
            const expanded = btn.getAttribute("aria-expanded") === "true";
            btn.setAttribute("aria-expanded", !expanded);
            answer.classList.toggle("hidden");

    
        });
    });

        
    });