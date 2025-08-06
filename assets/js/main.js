// Lucas Carpenter Landing Page - Interactive Features

// Header scroll behavior
function initScrollEffects() {
  const header = document.querySelector('.hero-header');
  const main = document.querySelector('main');
  let isScrolled = false;
  let ticking = false;

  function updateHeaderOnScroll() {
    const scrollY = window.scrollY;
    const scrollThreshold = 100; // Start effect after 100px of scroll

    if (scrollY > scrollThreshold && !isScrolled) {
      header.classList.add('scrolled');
      if (main) main.classList.add('content-lifted');
      isScrolled = true;
    } else if (scrollY <= scrollThreshold && isScrolled) {
      header.classList.remove('scrolled');
      if (main) main.classList.remove('content-lifted');
      isScrolled = false;
    }
    
    ticking = false;
  }

  function requestScrollUpdate() {
    if (!ticking) {
      requestAnimationFrame(updateHeaderOnScroll);
      ticking = true;
    }
  }

  // Add scroll event listener with throttling
  window.addEventListener('scroll', requestScrollUpdate, { passive: true });
}

// Enhanced scroll behavior for better UX
function initSmoothScrolling() {
  // Smooth scroll to sections when clicking internal links
  document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
      e.preventDefault();
      const target = document.querySelector(this.getAttribute('href'));
      if (target) {
        target.scrollIntoView({
          behavior: 'smooth',
          block: 'start'
        });
      }
    });
  });
}

// Directory card hover effects
function initCardEffects() {
  const cards = document.querySelectorAll('.dir-card');
  
  cards.forEach(card => {
    card.addEventListener('mouseenter', function() {
      // Add subtle scale effect
      this.style.transform = 'translateY(-8px) scale(1.02)';
    });
    
    card.addEventListener('mouseleave', function() {
      // Reset transform
      this.style.transform = '';
    });
  });
}

// Initialize all effects when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
  initScrollEffects();
  initSmoothScrolling();
  initCardEffects();
  
  // Add loading complete class for animations
  setTimeout(() => {
    document.body.classList.add('loaded');
  }, 100);
});

// Performance optimization: Reduce motion for users who prefer it
if (window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
  // Disable animations for accessibility
  document.documentElement.style.setProperty('--animation-duration', '0s');
}
