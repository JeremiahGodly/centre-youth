// Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam
// Main Application Controller

document.addEventListener('DOMContentLoaded', () => {
  // 1. Initial State
  let currentLang = localStorage.getItem('sakhyam_lang') || 'en';
  let currentTheme = localStorage.getItem('sakhyam_theme') || (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light');

  // Apply Initial Attributes
  document.documentElement.setAttribute('data-theme', currentTheme);
  document.documentElement.setAttribute('data-lang', currentLang);

  // Initialize Canvas Motion
  const aura = new CanvasAura('hero-canvas');
  const lightbox = new Lightbox();
  const audioVisualizer = new AudioPlayerVisualizer('assets/audio/centre-voice-snippet.m4a', 'btn-audio-toggle', 'audio-canvas');

  // 2. Language Switcher
  function setLanguage(lang) {
    currentLang = lang;
    localStorage.setItem('sakhyam_lang', lang);
    document.documentElement.setAttribute('data-lang', lang);

    // Update active button state
    document.querySelectorAll('.lang-btn').forEach(btn => {
      btn.classList.toggle('active', btn.getAttribute('data-lang-val') === lang);
    });

    // Update static translations in DOM
    applyTranslations();

    // Re-render dynamic sections with new language strings
    renderPillars();
    renderKalamelaItems();
    renderParishes();
    renderOfficeBearers();
    renderAchievements();
    renderGallery();
  }

  function applyTranslations() {
    const dict = I18N[currentLang] || I18N.en;

    document.querySelectorAll('[data-i18n]').forEach(el => {
      const key = el.getAttribute('data-i18n');
      if (dict[key]) {
        if (el.tagName === 'INPUT' || el.tagName === 'TEXTAREA') {
          el.placeholder = dict[key];
        } else {
          el.innerHTML = dict[key];
        }
      }
    });

    // Update document title
    document.title = dict.metaTitle || "Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam";
  }

  // 3. Theme Toggle
  const themeToggles = document.querySelectorAll('.theme-toggle');
  function toggleTheme() {
    currentTheme = currentTheme === 'dark' ? 'light' : 'dark';
    document.documentElement.setAttribute('data-theme', currentTheme);
    localStorage.setItem('sakhyam_theme', currentTheme);
    if (aura) aura.updateTheme(currentTheme);
    updateThemeIcons();
  }

  function updateThemeIcons() {
    themeToggles.forEach(btn => {
      const sunIcon = btn.querySelector('.icon-sun');
      const moonIcon = btn.querySelector('.icon-moon');
      if (sunIcon && moonIcon) {
        sunIcon.style.display = currentTheme === 'dark' ? 'block' : 'none';
        moonIcon.style.display = currentTheme === 'dark' ? 'none' : 'block';
      }
    });
  }

  themeToggles.forEach(btn => btn.addEventListener('click', toggleTheme));
  updateThemeIcons();

  // Language Button Clicks
  document.querySelectorAll('.lang-btn').forEach(btn => {
    btn.addEventListener('click', (e) => {
      setLanguage(btn.getAttribute('data-lang-val'));
    });
  });

  // 4. Mobile Drawer Toggle
  const mobileMenuBtn = document.getElementById('mobile-menu-btn');
  const mobileBottomMenuBtn = document.getElementById('mobile-bottom-menu-toggle');
  const mobileDrawer = document.getElementById('mobile-drawer');
  const drawerCloseBtn = document.getElementById('drawer-close');

  function openDrawer() {
    if (mobileDrawer) {
      mobileDrawer.classList.add('open');
      document.body.style.overflow = 'hidden';
    }
  }

  if (mobileMenuBtn) mobileMenuBtn.addEventListener('click', openDrawer);
  if (mobileBottomMenuBtn) mobileBottomMenuBtn.addEventListener('click', openDrawer);

  if (drawerCloseBtn && mobileDrawer) {
    drawerCloseBtn.addEventListener('click', () => {
      mobileDrawer.classList.remove('open');
      document.body.style.overflow = '';
    });
  }

  // Close drawer on clicking nav link
  document.querySelectorAll('#mobile-drawer .nav-link').forEach(link => {
    link.addEventListener('click', () => {
      mobileDrawer.classList.remove('open');
      document.body.style.overflow = '';
    });
  });

  // 5. Sticky Header Scroll Effect
  const header = document.querySelector('.site-header');
  window.addEventListener('scroll', () => {
    if (window.scrollY > 40) {
      header.classList.add('scrolled');
    } else {
      header.classList.remove('scrolled');
    }
  });

  // 6. Dynamic Section Renderers

  // Four Pillars
  function renderPillars() {
    const container = document.getElementById('pillars-grid');
    if (!container) return;
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const descKey = currentLang === 'ml' ? 'descMl' : (currentLang === 'mang' ? 'descMang' : 'descEn');

    container.innerHTML = SAKHYAM_DATA.pillars.map((p, idx) => `
      <div class="pillar-card tilt-card glass-panel" style="animation-delay: ${idx * 0.1}s">
        <div class="pillar-icon-badge">
          ${getPillarIconSvg(p.icon)}
        </div>
        <h3 class="pillar-title">${p[titleKey]}</h3>
        <p class="pillar-desc">${p[descKey]}</p>
        <div class="pillar-corner-glow"></div>
      </div>
    `).join('');

    initTiltEffect();
  }

  function getPillarIconSvg(type) {
    switch(type) {
      case 'flame':
        return `<svg viewBox="0 0 24 24" width="28" height="28" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M8.5 14.5A2.5 2.5 0 0 0 11 12c0-1.38-.5-2-1-3-1.072-2.143-.224-4.054 2-6 .5 2.5 2 4.9 4 6.5 2 1.6 3 3.5 3 5.5a7 7 0 1 1-14 0c0-1.153.433-2.294 1-3a2.5 2.5 0 0 0 2.5 2.5z"/></svg>`;
      case 'book-open':
        return `<svg viewBox="0 0 24 24" width="28" height="28" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M2 3h6a4 4 0 0 1 4 4v14a3 3 0 0 0-3-3H2z"/><path d="M22 3h-6a4 4 0 0 0-4 4v14a3 3 0 0 1 3-3h7z"/></svg>`;
      case 'compass':
        return `<svg viewBox="0 0 24 24" width="28" height="28" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><polygon points="16.24 7.76 14.12 14.12 7.76 16.24 9.88 9.88 16.24 7.76"/></svg>`;
      case 'heart-handshake':
        return `<svg viewBox="0 0 24 24" width="28" height="28" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M19 14c1.49-1.46 3-3.21 3-5.5A5.5 5.5 0 0 0 16.5 3c-1.76 0-3 .5-4.5 2-1.5-1.5-2.74-2-4.5-2A5.5 5.5 0 0 0 2 8.5c0 2.3 1.5 4.05 3 5.5l7 7Z"/></svg>`;
      default:
        return `<svg viewBox="0 0 24 24" width="28" height="28" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/></svg>`;
    }
  }

  // LUMORA Kalamela Competitions
  let activeKalamelaCategory = 'All';
  function renderKalamelaItems() {
    const container = document.getElementById('kalamela-items-grid');
    if (!container) return;

    const filtered = activeKalamelaCategory === 'All'
      ? SAKHYAM_DATA.kalamela.items
      : SAKHYAM_DATA.kalamela.items.filter(item => item.type === activeKalamelaCategory);

    const nameKey = currentLang === 'ml' ? 'nameMl' : (currentLang === 'mang' ? 'nameMang' : 'nameEn');
    const dict = I18N[currentLang] || I18N.en;

    container.innerHTML = filtered.map(item => `
      <div class="kalamela-item-card glass-panel">
        <div class="item-header">
          <span class="item-number">#${item.no}</span>
          <span class="item-badge-type">${item.type}</span>
        </div>
        <h4 class="item-name">${item[nameKey]}</h4>
        <div class="item-footer">
          <span class="item-duration">
            <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/></svg>
            ${dict.itemDuration} <strong>${item.duration}</strong>
          </span>
        </div>
      </div>
    `).join('');
  }

  // Kalamela Category Tabs
  document.querySelectorAll('.kalamela-tab-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('.kalamela-tab-btn').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      activeKalamelaCategory = btn.getAttribute('data-cat');
      renderKalamelaItems();
    });
  });

  // Parishes Directory
  let parishSearchQuery = '';
  function renderParishes() {
    const container = document.getElementById('parishes-grid');
    if (!container) return;

    const nameKey = currentLang === 'ml' ? 'nameMl' : (currentLang === 'mang' ? 'nameMang' : 'nameEn');
    const dict = I18N[currentLang] || I18N.en;

    const filtered = SAKHYAM_DATA.parishes.filter(p => {
      const q = parishSearchQuery.toLowerCase();
      return p.nameEn.toLowerCase().includes(q) ||
             p.nameMl.toLowerCase().includes(q) ||
             p.nameMang.toLowerCase().includes(q) ||
             p.location.toLowerCase().includes(q);
    });

    if (filtered.length === 0) {
      container.innerHTML = `
        <div class="empty-state">
          <p>No parishes matching "${parishSearchQuery}".</p>
        </div>
      `;
      return;
    }

    container.innerHTML = filtered.map(p => `
      <div class="parish-card glass-panel tilt-card">
        <div class="parish-card-header">
          <div class="parish-icon">
            <svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="2"><path d="M18 20V6a2 2 0 0 0-2-2H8a2 2 0 0 0-2 2v14"/><path d="M2 20h20"/><path d="M14 12v.01"/><path d="M12 2v4"/><path d="M10 4h4"/></svg>
          </div>
          <span class="parish-tag">${p.tag}</span>
        </div>
        <h4 class="parish-title">${p[nameKey]}</h4>
        <div class="parish-meta">
          <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" stroke-width="2"><path d="M20 10c0 6-8 12-8 12s-8-6-8-12a8 8 0 0 1 16 0Z"/><circle cx="12" cy="10" r="3"/></svg>
          <span>${p.location}</span>
        </div>
        <div class="parish-footer">
          <span class="parish-badge">${p.badge}</span>
          <a href="https://maps.google.com/?q=${encodeURIComponent(p.nameEn + ' ' + p.location)}" target="_blank" rel="noopener" class="parish-map-btn" title="${dict.btnLocate}">
            <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" stroke-width="2"><polygon points="3 11 22 2 13 21 11 13 3 11"/></svg>
          </a>
        </div>
      </div>
    `).join('');

    initTiltEffect();
  }

  // Parish Search Input
  const parishSearchInput = document.getElementById('parish-search-input');
  if (parishSearchInput) {
    parishSearchInput.addEventListener('input', (e) => {
      parishSearchQuery = e.target.value.trim();
      renderParishes();
    });
  }

  // Office Bearers
  function renderOfficeBearers() {
    const container = document.getElementById('leaders-grid');
    if (!container) return;

    const nameKey = currentLang === 'ml' ? 'nameMl' : (currentLang === 'mang' ? 'nameMang' : 'nameEn');
    const roleKey = currentLang === 'ml' ? 'roleMl' : (currentLang === 'mang' ? 'roleMang' : 'roleEn');
    const desigKey = currentLang === 'ml' ? 'designationMl' : (currentLang === 'mang' ? 'designationMang' : 'designationEn');

    container.innerHTML = SAKHYAM_DATA.officeBearers.map(b => `
      <div class="leader-card glass-panel tilt-card">
        <div class="leader-avatar-wrap">
          <img src="${b.image}" alt="${b[nameKey]}" class="leader-avatar" loading="lazy" />
          <span class="leader-role-badge">${b[roleKey]}</span>
        </div>
        <div class="leader-details">
          <h4 class="leader-name">${b[nameKey]}</h4>
          <p class="leader-designation">${b[desigKey]}</p>
          <span class="leader-subtag">${b.badge}</span>
        </div>
      </div>
    `).join('');

    initTiltEffect();
  }

  // Achievements
  function renderAchievements() {
    const container = document.getElementById('achievements-grid');
    if (!container) return;

    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const descKey = currentLang === 'ml' ? 'descMl' : (currentLang === 'mang' ? 'descMang' : 'descEn');

    container.innerHTML = SAKHYAM_DATA.achievements.map((a, i) => `
      <div class="achievement-card glass-panel tilt-card">
        <div class="achievement-img-wrap" onclick="openSingleImage('${a.image}', '${a[titleKey].replace(/'/g, "\\'")}')">
          <img src="${a.image}" alt="${a[titleKey]}" loading="lazy" />
          <span class="achievement-badge">${a.badge}</span>
          <div class="img-zoom-hint">
            <svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2"><circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/><line x1="11" y1="8" x2="11" y2="14"/><line x1="8" y1="11" x2="14" y2="11"/></svg>
          </div>
        </div>
        <div class="achievement-content">
          <h4 class="achievement-title">${a[titleKey]}</h4>
          <p class="achievement-desc">${a[descKey]}</p>
        </div>
      </div>
    `).join('');

    initTiltEffect();
  }

  // Gallery
  let activeGalleryCategory = 'all';
  function renderGallery() {
    const container = document.getElementById('gallery-grid');
    if (!container) return;

    const filtered = activeGalleryCategory === 'all'
      ? SAKHYAM_DATA.gallery
      : SAKHYAM_DATA.gallery.filter(g => g.category === activeGalleryCategory);

    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');

    container.innerHTML = filtered.map((item, index) => `
      <div class="gallery-item glass-panel" onclick="openGalleryItem(${index})">
        <img src="${item.src}" alt="${item[titleKey]}" loading="lazy" />
        <div class="gallery-overlay">
          <span class="gallery-cat">${item.category.toUpperCase()}</span>
          <h5 class="gallery-item-title">${item[titleKey]}</h5>
          <div class="gallery-view-icon">
            <svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="2"><polyline points="15 3 21 3 21 9"/><polyline points="9 21 3 21 3 15"/><line x1="21" y1="3" x2="14" y2="10"/><line x1="3" y1="21" x2="10" y2="14"/></svg>
          </div>
        </div>
      </div>
    `).join('');
  }

  // Gallery Filter Tabs
  document.querySelectorAll('.gallery-tab-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('.gallery-tab-btn').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      activeGalleryCategory = btn.getAttribute('data-gallery-filter');
      renderGallery();
    });
  });

  window.openGalleryItem = function(index) {
    const filtered = activeGalleryCategory === 'all'
      ? SAKHYAM_DATA.gallery
      : SAKHYAM_DATA.gallery.filter(g => g.category === activeGalleryCategory);
    lightbox.open(filtered, index);
  };

  window.openSingleImage = function(src, title) {
    lightbox.open([{ src, titleEn: title, titleMl: title, titleMang: title }], 0);
  };

  // 7. Animated Counter with IntersectionObserver
  function initCounters() {
    const counters = document.querySelectorAll('.stat-number');
    const observer = new IntersectionObserver((entries, obs) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          const target = parseInt(entry.target.getAttribute('data-count'), 10);
          const suffix = entry.target.getAttribute('data-suffix') || '';
          animateCounter(entry.target, target, suffix);
          obs.unobserve(entry.target);
        }
      });
    }, { threshold: 0.5 });

    counters.forEach(c => observer.observe(c));
  }

  function animateCounter(el, target, suffix) {
    let start = 0;
    const duration = 1800;
    const startTime = performance.now();

    function update(time) {
      const elapsed = time - startTime;
      const progress = Math.min(elapsed / duration, 1);
      // easeOutExpo
      const ease = progress === 1 ? 1 : 1 - Math.pow(2, -10 * progress);
      const current = Math.floor(ease * target);
      el.textContent = current + suffix;

      if (progress < 1) {
        requestAnimationFrame(update);
      } else {
        el.textContent = target + suffix;
      }
    }

    requestAnimationFrame(update);
  }

  // 8. Contact / Registration Form Handler
  const contactForm = document.getElementById('sakhyam-contact-form');
  const toastNotification = document.getElementById('toast-notification');

  if (contactForm) {
    contactForm.addEventListener('submit', (e) => {
      e.preventDefault();
      const submitBtn = contactForm.querySelector('button[type="submit"]');
      submitBtn.disabled = true;
      submitBtn.classList.add('loading');

      setTimeout(() => {
        submitBtn.disabled = false;
        submitBtn.classList.remove('loading');
        contactForm.reset();

        // Show Toast
        const dict = I18N[currentLang] || I18N.en;
        toastNotification.textContent = dict.formSuccess;
        toastNotification.classList.add('show');

        setTimeout(() => {
          toastNotification.classList.remove('show');
        }, 5000);
      }, 900);
    });
  }

  // 9. Initial Renders
  applyTranslations();
  renderPillars();
  renderKalamelaItems();
  renderParishes();
  renderOfficeBearers();
  renderAchievements();
  renderGallery();
  initCounters();
  initTiltEffect();
  if (typeof initFlyingLogoScroll === 'function') {
    initFlyingLogoScroll();
  }
});
