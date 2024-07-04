// script.js

document.addEventListener('DOMContentLoaded', () => {
    // Toggle the .active class on the nav-links when the hamburger is clicked
    const hamburger = document.querySelector('.hamburger');
    const navLinks = document.querySelector('.nav-links');

    hamburger.addEventListener('click', () => {
        navLinks.classList.toggle('active');
    });

    // Optional: Close the nav menu when a link is clicked (mobile view)
    const links = document.querySelectorAll('.nav-links a');
    links.forEach(link => {
        link.addEventListener('click', () => {
            navLinks.classList.remove('active');
        });
    });

    // Sticky header functionality
    const header = document.querySelector('header');
    window.addEventListener('scroll', () => {
        if (window.scrollY > 0) {
            header.classList.add('sticky');
        } else {
            header.classList.remove('sticky');
        }
    });

    // Reveal sections on scroll
    const sections = document.querySelectorAll('section');
    const revealSection = () => {
        const revealPoint = 150;
        sections.forEach(section => {
            const sectionTop = section.getBoundingClientRect().top;
            if (sectionTop < window.innerHeight - revealPoint) {
                section.classList.add('reveal');
            } else {
                section.classList.remove('reveal');
            }
        });
    };
    window.addEventListener('scroll', revealSection);

    // Initial call to reveal sections if already in view
    revealSection();

    // CONTACT ME OVERLAY CODE
    const contactForm = document.getElementById('contactForm');
    const overlay = document.getElementById('overlay');
    const closeButton = document.querySelector('.close');

    // Hide overlay initially
    overlay.style.display = 'none';

    contactForm.addEventListener('submit', (event) => {
        event.preventDefault(); // Prevent form submission

        // Assuming the form is validated and submitted successfully
        displayOverlay();
    });

    closeButton.addEventListener('click', () => {
        closeOverlay();
    });

    function displayOverlay() {
        overlay.style.display = 'flex'; // Display overlay as flex (or block if preferred)
    }

    function closeOverlay() {
        overlay.style.display = 'none'; // Hide overlay
    }

    // Function to close overlay if clicked outside of content
    window.onclick = function(event) {
        if (event.target == overlay) {
            closeOverlay();
        }
    }

    // Reset overlay on page load or refresh
    window.onload = function() {
        closeOverlay();
    }
});
