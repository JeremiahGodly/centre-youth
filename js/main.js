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
    renderLectionary();
    renderUpcomingLectionaryWidget();
    renderSongs();
    renderHomeSongsWidget();
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
  let galleryDisplayCount = 6;

  function renderGallery() {
    const container = document.getElementById('gallery-grid');
    if (!container) return;

    const filtered = activeGalleryCategory === 'all'
      ? SAKHYAM_DATA.gallery
      : SAKHYAM_DATA.gallery.filter(g => g.category === activeGalleryCategory);

    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const visibleItems = filtered.slice(0, galleryDisplayCount);

    let html = visibleItems.map((item, index) => `
      <div class="gallery-item glass-panel" onclick="openGalleryItem(${index})">
        <img src="${item.src}" alt="${item[titleKey]}" loading="lazy" decoding="async" />
        <div class="gallery-overlay">
          <span class="gallery-cat">${item.category.toUpperCase()}</span>
          <h5 class="gallery-item-title">${item[titleKey]}</h5>
          <div class="gallery-view-icon">
            <svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="2"><polyline points="15 3 21 3 21 9"/><polyline points="9 21 3 21 3 15"/><line x1="21" y1="3" x2="14" y2="10"/><line x1="3" y1="21" x2="10" y2="14"/></svg>
          </div>
        </div>
      </div>
    `).join('');

    if (filtered.length > galleryDisplayCount) {
      const remaining = filtered.length - galleryDisplayCount;
      html += `
        <div class="gallery-load-more-wrap">
          <button type="button" class="btn-load-more" onclick="loadMoreGallery()">
            <svg viewBox="0 0 24 24" width="18" height="18" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/><polyline points="8 12 12 16 16 12"/><line x1="12" y1="8" x2="12" y2="16"/></svg>
            <span>Load More Photos (${remaining} More) 📸</span>
          </button>
        </div>
      `;
    }

    container.innerHTML = html;
  }

  window.loadMoreGallery = function() {
    galleryDisplayCount += 6;
    renderGallery();
  };

  // Gallery Filter Tabs
  document.querySelectorAll('.gallery-tab-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('.gallery-tab-btn').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      activeGalleryCategory = btn.getAttribute('data-gallery-filter');
      galleryDisplayCount = 6;
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

  // 7b. Lectionary Controller (Automated Upcoming Week Calculation & Glowing)
  let activeLectionaryMonth = 'all';
  let lectionarySearchQuery = '';

  function getUpcomingLectionaryEntry() {
    if (!SAKHYAM_DATA.lectionary || SAKHYAM_DATA.lectionary.length === 0) return null;
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const todayStr = `${year}-${month}-${day}`;

    // Find the first lectionary entry whose date >= today
    const upcoming = SAKHYAM_DATA.lectionary.find(item => item.date >= todayStr);
    return upcoming || SAKHYAM_DATA.lectionary[0];
  }

  function renderLectionary() {
    const container = document.getElementById('lectionary-items-grid');
    if (!container) return;

    const dict = I18N[currentLang] || I18N.en;
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const themeKey = currentLang === 'ml' ? 'themeMl' : (currentLang === 'mang' ? 'themeMang' : 'themeEn');
    const seasonKey = currentLang === 'ml' ? 'seasonMl' : (currentLang === 'mang' ? 'seasonMang' : 'seasonEn');
    const dateKey = currentLang === 'ml' ? 'displayDateMl' : (currentLang === 'mang' ? 'displayDateMang' : 'displayDateEn');

    const upcomingEntry = getUpcomingLectionaryEntry();
    const upcomingId = upcomingEntry ? upcomingEntry.id : null;

    // Filter by month and search query
    let filtered = SAKHYAM_DATA.lectionary;
    if (activeLectionaryMonth !== 'all') {
      filtered = filtered.filter(item => item.monthKey === activeLectionaryMonth);
    }
    if (lectionarySearchQuery.trim()) {
      const q = lectionarySearchQuery.toLowerCase().trim();
      filtered = filtered.filter(item => {
        const text = `${item.titleEn} ${item.titleMl} ${item.titleMang} ${item.themeEn} ${item.themeMl} ${item.readings.lesson1} ${item.readings.lesson2} ${item.readings.epistle} ${item.readings.gospel}`.toLowerCase();
        return text.includes(q);
      });
    }

    if (filtered.length === 0) {
      container.innerHTML = `
        <div class="empty-state glass-panel" style="grid-column: 1 / -1; padding: 3rem; text-align: center;">
          <p style="color: var(--text-muted); font-size: 1.1rem;">No lectionary readings found matching your search.</p>
        </div>
      `;
      return;
    }

    container.innerHTML = filtered.map(item => {
      const isNext = item.id === upcomingId;
      const cardClass = isNext ? 'lectionary-card glow-next-week' : 'lectionary-card';

      return `
        <article class="${cardClass}" id="lectionary-${item.id}">
          ${isNext ? `
            <div class="glow-beacon-banner">
              <div style="display: flex; align-items: center; gap: 0.4rem;">
                <span class="beacon-dot"></span>
                <span>${dict.nextUpcomingBadge}</span>
              </div>
              <span style="opacity: 0.9;">${item.date}</span>
            </div>
          ` : ''}

          <div class="card-date-badge">
            <span class="card-date-str">${item[dateKey]}</span>
            <span class="card-season-pill">${item[seasonKey]}</span>
          </div>

          <h3 class="lectionary-card-title">${item[titleKey]}</h3>
          <p class="lectionary-card-theme">${item[themeKey]}</p>

          <div class="readings-container">
            <div class="reading-row">
              <span class="reading-type">${dict.lesson1Label}</span>
              <span class="reading-verse">${item.readings.lesson1}</span>
            </div>
            <div class="reading-row">
              <span class="reading-type">${dict.lesson2Label}</span>
              <span class="reading-verse">${item.readings.lesson2}</span>
            </div>
            <div class="reading-row">
              <span class="reading-type">${dict.epistleLabel}</span>
              <span class="reading-verse">${item.readings.epistle}</span>
            </div>
            <div class="reading-row gospel-row">
              <span class="reading-type">${dict.gospelLabel}</span>
              <span class="reading-verse">${item.readings.gospel}</span>
            </div>
            ${item.readings.evening1 ? `
              <div class="reading-row">
                <span class="reading-type">${dict.evening1Label}</span>
                <span class="reading-verse">${item.readings.evening1}</span>
              </div>
            ` : ''}
            ${item.readings.evening2 ? `
              <div class="reading-row">
                <span class="reading-type">${dict.evening2Label}</span>
                <span class="reading-verse">${item.readings.evening2}</span>
              </div>
            ` : ''}
          </div>

          <div class="card-actions">
            <button type="button" class="btn-card-action" onclick="copyLectionaryReading('${item.id}')">
              <svg viewBox="0 0 24 24" width="14" height="14" fill="none" stroke="currentColor" stroke-width="2"><rect x="9" y="9" width="13" height="13" rx="2" ry="2"/><path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"/></svg>
              <span>${dict.copyReadings}</span>
            </button>
            <a href="${getWhatsAppShareUrl(item)}" target="_blank" rel="noopener" class="btn-card-action btn-card-whatsapp">
              <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><path d="M12.04 2c-5.46 0-9.91 4.45-9.91 9.91 0 1.75.46 3.45 1.32 4.95L2.05 22l5.25-1.38c1.45.79 3.08 1.21 4.74 1.21 5.46 0 9.91-4.45 9.91-9.91 0-2.65-1.03-5.14-2.9-7.01A9.816 9.816 0 0 0 12.04 2zm.01 1.67c4.54 0 8.24 3.7 8.24 8.24 0 2.2-.86 4.28-2.42 5.84-1.56 1.56-3.64 2.41-5.83 2.41-1.45 0-2.87-.39-4.13-1.12l-.3-.18-3.07.81.82-2.99-.19-.32c-.8-1.32-1.23-2.84-1.23-4.44 0-4.54 3.7-8.24 8.23-8.24zm4.52 11.66c-.25-.13-1.47-.72-1.7-.81-.23-.08-.39-.13-.56.13-.17.25-.64.81-.79.97-.14.17-.29.19-.54.06-.25-.13-1.06-.39-2.02-1.25-.75-.67-1.25-1.5-1.4-1.75-.15-.25-.02-.39.11-.51.11-.11.25-.29.38-.44.13-.15.17-.25.25-.42.08-.17.04-.31-.02-.44-.06-.13-.56-1.35-.77-1.85-.2-.49-.41-.42-.56-.43h-.48c-.17 0-.44.06-.67.31-.23.25-.88.86-.88 2.1 0 1.24.9 2.44 1.03 2.61.13.17 1.77 2.7 4.29 3.79.6.26 1.07.41 1.43.53.6.19 1.15.16 1.59.1.48-.07 1.47-.6 1.68-1.18.21-.58.21-1.07.15-1.18-.06-.11-.22-.17-.47-.3z"/></svg>
              <span>${dict.shareToWhatsapp}</span>
            </a>
          </div>
        </article>
      `;
    }).join('');
  }

  function renderUpcomingLectionaryWidget() {
    const container = document.getElementById('home-upcoming-lectionary-widget');
    if (!container) return;

    const item = getUpcomingLectionaryEntry();
    if (!item) return;

    const dict = I18N[currentLang] || I18N.en;
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const themeKey = currentLang === 'ml' ? 'themeMl' : (currentLang === 'mang' ? 'themeMang' : 'themeEn');
    const seasonKey = currentLang === 'ml' ? 'seasonMl' : (currentLang === 'mang' ? 'seasonMang' : 'seasonEn');
    const dateKey = currentLang === 'ml' ? 'displayDateMl' : (currentLang === 'mang' ? 'displayDateMang' : 'displayDateEn');

    container.innerHTML = `
      <div class="lectionary-card glow-next-week" style="max-width: 680px; margin: 0 auto;">
        <div class="glow-beacon-banner">
          <div style="display: flex; align-items: center; gap: 0.4rem;">
            <span class="beacon-dot"></span>
            <span>${dict.nextUpcomingBadge}</span>
          </div>
          <span style="opacity: 0.9;">${item.date}</span>
        </div>

        <div class="card-date-badge">
          <span class="card-date-str">${item[dateKey]}</span>
          <span class="card-season-pill">${item[seasonKey]}</span>
        </div>

        <h3 class="lectionary-card-title">${item[titleKey]}</h3>
        <p class="lectionary-card-theme">${item[themeKey]}</p>

        <div class="readings-container">
          <div class="reading-row">
            <span class="reading-type">${dict.lesson1Label}</span>
            <span class="reading-verse">${item.readings.lesson1}</span>
          </div>
          <div class="reading-row">
            <span class="reading-type">${dict.lesson2Label}</span>
            <span class="reading-verse">${item.readings.lesson2}</span>
          </div>
          <div class="reading-row">
            <span class="reading-type">${dict.epistleLabel}</span>
            <span class="reading-verse">${item.readings.epistle}</span>
          </div>
          <div class="reading-row gospel-row">
            <span class="reading-type">${dict.gospelLabel}</span>
            <span class="reading-verse">${item.readings.gospel}</span>
          </div>
        </div>

        <div class="card-actions" style="margin-top: 0.5rem;">
          <a href="lectionary.html" class="btn btn-primary" style="flex: 1; justify-content: center; font-size: 0.85rem; padding: 0.65rem 1rem;">
            <span>${dict.openFullLectionary}</span>
          </a>
          <button type="button" class="btn-card-action" onclick="copyLectionaryReading('${item.id}')" style="flex: 0 0 auto; padding: 0.65rem 1rem;">
            <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" stroke-width="2"><rect x="9" y="9" width="13" height="13" rx="2" ry="2"/><path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"/></svg>
            <span>${dict.copyReadings}</span>
          </button>
        </div>
      </div>
    `;
  }

  window.scrollToUpcomingWeek = function() {
    const card = document.querySelector('.glow-next-week');
    if (card) {
      card.scrollIntoView({ behavior: 'smooth', block: 'center' });
      card.style.transition = 'box-shadow 0.3s ease';
      card.style.boxShadow = '0 0 45px rgba(245, 158, 11, 0.9), 0 0 80px rgba(6, 182, 212, 0.7)';
      setTimeout(() => {
        card.style.boxShadow = '';
      }, 1800);
    }
  };

  function getWhatsAppShareUrl(item) {
    const dateKey = currentLang === 'ml' ? 'displayDateMl' : (currentLang === 'mang' ? 'displayDateMang' : 'displayDateEn');
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const themeKey = currentLang === 'ml' ? 'themeMl' : (currentLang === 'mang' ? 'themeMang' : 'themeEn');

    const msg = `*Mar Thoma Liturgical Lectionary*\n📅 *${item[dateKey]}*\n📖 *${item[titleKey]}*\n✨ _${item[themeKey]}_\n\n• Lesson 1: ${item.readings.lesson1}\n• Lesson 2: ${item.readings.lesson2}\n• Epistle: ${item.readings.epistle}\n• Gospel: ${item.readings.gospel}\n\nKeezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam`;
    return `https://wa.me/?text=${encodeURIComponent(msg)}`;
  }

  window.copyLectionaryReading = function(id) {
    const item = SAKHYAM_DATA.lectionary.find(i => i.id === id);
    if (!item) return;
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const themeKey = currentLang === 'ml' ? 'themeMl' : (currentLang === 'mang' ? 'themeMang' : 'themeEn');
    const dateKey = currentLang === 'ml' ? 'displayDateMl' : (currentLang === 'mang' ? 'displayDateMang' : 'displayDateEn');

    const text = `${item[dateKey]} - ${item[titleKey]}\nTheme: ${item[themeKey]}\nLesson 1: ${item.readings.lesson1}\nLesson 2: ${item.readings.lesson2}\nEpistle: ${item.readings.epistle}\nGospel: ${item.readings.gospel}\n\nKeezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam`;

    navigator.clipboard.writeText(text).then(() => {
      const dict = I18N[currentLang] || I18N.en;
      if (toastNotification) {
        toastNotification.textContent = dict.copiedSuccess || "Scripture readings copied to clipboard!";
        toastNotification.classList.add('show');
        setTimeout(() => toastNotification.classList.remove('show'), 3500);
      }
    });
  };

  // Lectionary Filter Tabs & Search Listeners
  document.querySelectorAll('.lectionary-filter-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('.lectionary-filter-btn').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      activeLectionaryMonth = btn.getAttribute('data-month-filter');
      renderLectionary();
    });
  });

  const lectionarySearchInput = document.getElementById('lectionary-search-input');
  if (lectionarySearchInput) {
    lectionarySearchInput.addEventListener('input', (e) => {
      lectionarySearchQuery = e.target.value;
      renderLectionary();
    });
  }

  const btnJumpNext = document.getElementById('btn-jump-next');
  if (btnJumpNext) {
    btnJumpNext.addEventListener('click', () => {
      scrollToUpcomingWeek();
    });
  }

  // ==========================================
  // 8. YOUTH SONGS REAL AUDIO ENGINE & CONTROLLER
  // ==========================================
  let activeSongCategory = 'all';
  let songSearchQuery = '';
  let currentPlayingSongIndex = -1;
  let isSongAudioPlaying = false;
  let songsDisplayCount = 12;

  // Real HTML5 Audio Engine
  let audioEngine = document.getElementById('sakhyam-audio-engine');
  if (!audioEngine) {
    audioEngine = new Audio();
    audioEngine.id = 'sakhyam-audio-engine';
    audioEngine.preload = 'none';
    document.body.appendChild(audioEngine);
  }

  function formatAudioTime(seconds) {
    if (isNaN(seconds) || seconds < 0) return '0:00';
    const mins = Math.floor(seconds / 60);
    const secs = Math.floor(seconds % 60);
    return `${mins}:${secs < 10 ? '0' : ''}${secs}`;
  }

  // Audio Engine Real-time Events
  audioEngine.addEventListener('play', () => {
    isSongAudioPlaying = true;
    updateBarPlayPauseIcons(true);
    const floatingBar = document.getElementById('floating-audio-bar');
    if (floatingBar) floatingBar.classList.add('is-playing', 'active');
    updateAllSongCardButtons();
  });

  audioEngine.addEventListener('pause', () => {
    isSongAudioPlaying = false;
    updateBarPlayPauseIcons(false);
    const floatingBar = document.getElementById('floating-audio-bar');
    if (floatingBar) floatingBar.classList.remove('is-playing');
    updateAllSongCardButtons();
  });

  audioEngine.addEventListener('timeupdate', () => {
    const cur = audioEngine.currentTime || 0;
    const dur = audioEngine.duration || 0;
    const pct = dur > 0 ? (cur / dur) * 100 : 0;

    const progressFill = document.getElementById('audio-progress-fill');
    if (progressFill) progressFill.style.width = `${pct}%`;

    const scrubber = document.getElementById('audio-scrubber');
    if (scrubber && !scrubber.matches(':active')) {
      scrubber.value = pct;
    }

    const timeCur = document.getElementById('audio-time-current');
    if (timeCur) timeCur.textContent = formatAudioTime(cur);

    const timeDur = document.getElementById('audio-time-duration');
    if (timeDur && dur > 0) timeDur.textContent = formatAudioTime(dur);
  });

  audioEngine.addEventListener('loadedmetadata', () => {
    const timeDur = document.getElementById('audio-time-duration');
    if (timeDur && audioEngine.duration > 0) {
      timeDur.textContent = formatAudioTime(audioEngine.duration);
    }
  });

  audioEngine.addEventListener('ended', () => {
    nextSong();
  });

  audioEngine.addEventListener('error', () => {
    console.warn('Audio streaming encountered error, attempting fallback URL.');
    if (currentPlayingSongIndex >= 0 && SAKHYAM_DATA.songs[currentPlayingSongIndex]) {
      const song = SAKHYAM_DATA.songs[currentPlayingSongIndex];
      const fallbackUrl = `https://docs.google.com/uc?export=download&id=${song.fileId}`;
      if (audioEngine.src !== fallbackUrl) {
        audioEngine.src = fallbackUrl;
        audioEngine.load();
        audioEngine.play().catch(e => console.warn('Fallback stream error:', e));
      }
    }
  });

  // Scrubber user input seek
  const audioScrubber = document.getElementById('audio-scrubber');
  if (audioScrubber) {
    audioScrubber.addEventListener('input', (e) => {
      const pct = parseFloat(e.target.value) || 0;
      if (audioEngine.duration) {
        audioEngine.currentTime = (pct / 100) * audioEngine.duration;
      }
    });
  }

  const audioProgressTrack = document.querySelector('.audio-bar-progress-track');
  if (audioProgressTrack) {
    audioProgressTrack.addEventListener('click', (e) => {
      const rect = audioProgressTrack.getBoundingClientRect();
      const clickX = e.clientX - rect.left;
      const pct = Math.max(0, Math.min(1, clickX / rect.width));
      if (audioEngine.duration) {
        audioEngine.currentTime = pct * audioEngine.duration;
      }
    });
  }

  function renderSongs() {
    const grid = document.getElementById('songs-grid');
    if (!grid || !SAKHYAM_DATA.songs) return;

    const dict = I18N[currentLang] || I18N.en;
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');

    const filtered = SAKHYAM_DATA.songs.filter(song => {
      const matchCat = activeSongCategory === 'all' || song.category.toLowerCase() === activeSongCategory.toLowerCase();
      const q = songSearchQuery.trim().toLowerCase();
      if (!q) return matchCat;

      const titleMatch = (song.titleEn && song.titleEn.toLowerCase().includes(q)) ||
                         (song.titleMl && song.titleMl.toLowerCase().includes(q)) ||
                         (song.titleMang && song.titleMang.toLowerCase().includes(q));
      const catMatch = song.category && song.category.toLowerCase().includes(q);
      const trackMatch = String(song.trackNo).includes(q) || String(song.rawNumber).includes(q);
      const artistMatch = song.artist && song.artist.toLowerCase().includes(q);

      return matchCat && (titleMatch || catMatch || trackMatch || artistMatch);
    });

    const isSearching = songSearchQuery.trim().length > 0;
    const visibleSongs = isSearching ? filtered : filtered.slice(0, songsDisplayCount);

    const countIndicator = document.getElementById('songs-count-indicator');
    if (countIndicator) {
      countIndicator.textContent = `Showing ${visibleSongs.length} of ${filtered.length} Tracks`;
    }

    if (filtered.length === 0) {
      grid.innerHTML = `
        <div style="grid-column: 1 / -1; text-align: center; padding: 3rem 1rem; color: var(--text-muted);">
          <svg viewBox="0 0 24 24" width="48" height="48" fill="none" stroke="currentColor" stroke-width="1.5" style="margin-bottom: 0.75rem; opacity: 0.6;"><circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/></svg>
          <p style="font-size: 1rem; font-weight: 600;">No audio tracks found matching "${songSearchQuery}"</p>
          <button type="button" class="btn btn-secondary btn-sm" onclick="resetSongSearch()" style="margin-top: 0.75rem;">Clear Filter</button>
        </div>
      `;
      return;
    }

    let cardsHtml = visibleSongs.map((song) => {
      const globalIdx = SAKHYAM_DATA.songs.findIndex(s => s.id === song.id);
      const isCurrent = globalIdx === currentPlayingSongIndex;
      const isPlaying = isCurrent && !audioEngine.paused;
      const padNo = String(song.trackNo).padStart(2, '0');

      return `
        <article class="song-card ${isCurrent ? 'is-playing' : ''}" data-song-index="${globalIdx}" id="song-card-${globalIdx}">
          <div class="song-card-header">
            <span class="song-track-badge">#${padNo}</span>
            <span class="song-category-pill">${song.category}</span>
          </div>

          <div class="song-card-body">
            <h3 class="song-card-title">${song[titleKey] || song.titleEn}</h3>
            <span class="song-card-artist">${song.artist}</span>
            <span class="song-card-collection">${song.collection}</span>
          </div>

          <div class="song-card-actions">
            <button type="button" class="btn-song-action btn-song-play" onclick="playSong(${globalIdx})">
              ${isPlaying ? `
                <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><rect x="6" y="4" width="4" height="16"/><rect x="14" y="4" width="4" height="16"/></svg>
                <span>${dict.pauseTrack || "Pause"}</span>
              ` : `
                <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><polygon points="6 3 20 12 6 21 6 3"/></svg>
                <span>${dict.playTrack || "Play Track"}</span>
              `}
            </button>
            <a href="${song.driveUrl}" target="_blank" rel="noopener" class="btn-song-action" title="Open in Google Drive">
              <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><path d="M19.35 10.04C18.67 6.59 15.64 4 12 4 9.11 4 6.6 5.64 5.35 8.04 2.34 8.36 0 10.91 0 14c0 3.31 2.69 6 6 6h13c2.76 0 5-2.24 5-5 0-2.64-2.05-4.78-4.65-4.96zM17 13l-5 5-5-5h3V9h4v4h3z"/></svg>
              <span>Drive</span>
            </a>
            <button type="button" class="btn-song-action" onclick="shareSongWhatsApp(${globalIdx})" title="Share to WhatsApp">
              <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><path d="M12.04 2c-5.46 0-9.91 4.45-9.91 9.91 0 1.75.46 3.45 1.32 4.95L2.05 22l5.25-1.38c1.45.79 3.08 1.21 4.74 1.21 5.46 0 9.91-4.45 9.91-9.91 0-2.65-1.03-5.14-2.9-7.01A9.816 9.816 0 0 0 12.04 2zm.01 1.67c4.54 0 8.24 3.7 8.24 8.24 0 2.2-.86 4.28-2.42 5.84-1.56 1.56-3.64 2.41-5.83 2.41-1.45 0-2.87-.39-4.13-1.12l-.3-.18-3.07.81.82-2.99-.19-.32c-.8-1.32-1.23-2.84-1.23-4.44 0-4.54 3.7-8.24 8.23-8.24zm4.52 11.66c-.25-.13-1.47-.72-1.7-.81-.23-.08-.39-.13-.56.13-.17.25-.64.81-.79.97-.14.17-.29.19-.54.06-.25-.13-1.06-.39-2.02-1.25-.75-.67-1.25-1.5-1.4-1.75-.15-.25-.02-.39.11-.51.11-.11.25-.29.38-.44.13-.15.17-.25.25-.42.08-.17.04-.31-.02-.44-.06-.13-.56-1.35-.77-1.85-.2-.49-.41-.42-.56-.43h-.48c-.17 0-.44.06-.67.31-.23.25-.88.86-.88 2.1 0 1.24.9 2.44 1.03 2.61.13.17 1.77 2.7 4.29 3.79.6.26 1.07.41 1.43.53.6.19 1.15.16 1.59.1.48-.07 1.47-.6 1.68-1.18.21-.58.21-1.07.15-1.18-.06-.11-.22-.17-.47-.3z"/></svg>
              <span>Share</span>
            </button>
          </div>
        </article>
      `;
    }).join('');

    if (!isSearching && filtered.length > songsDisplayCount) {
      const remaining = filtered.length - songsDisplayCount;
      cardsHtml += `
        <div class="songs-load-more-wrap">
          <button type="button" class="btn-load-more" onclick="loadMoreSongs()">
            <svg viewBox="0 0 24 24" width="18" height="18" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/><polyline points="8 12 12 16 16 12"/><line x1="12" y1="8" x2="12" y2="16"/></svg>
            <span>Load More Songs (${remaining} More) 🎵</span>
          </button>
        </div>
      `;
    }

    grid.innerHTML = cardsHtml;
  }

  window.loadMoreSongs = function() {
    songsDisplayCount += 12;
    renderSongs();
  };

  function renderHomeSongsWidget() {
    const container = document.getElementById('home-songs-widget');
    if (!container || !SAKHYAM_DATA.songs) return;

    const dict = I18N[currentLang] || I18N.en;
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');

    const sampleSongs = SAKHYAM_DATA.songs.slice(0, 4);

    container.innerHTML = sampleSongs.map((song) => {
      const globalIdx = SAKHYAM_DATA.songs.findIndex(s => s.id === song.id);
      const isCurrent = globalIdx === currentPlayingSongIndex;
      const isPlaying = isCurrent && !audioEngine.paused;
      const padNo = String(song.trackNo).padStart(2, '0');

      return `
        <article class="song-card ${isCurrent ? 'is-playing' : ''}" data-song-index="${globalIdx}">
          <div class="song-card-header">
            <span class="song-track-badge">#${padNo}</span>
            <span class="song-category-pill">${song.category}</span>
          </div>

          <div class="song-card-body">
            <h3 class="song-card-title">${song[titleKey] || song.titleEn}</h3>
            <span class="song-card-artist">${song.artist}</span>
          </div>

          <div class="song-card-actions">
            <button type="button" class="btn-song-action btn-song-play" onclick="playSong(${globalIdx})">
              ${isPlaying ? `
                <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><rect x="6" y="4" width="4" height="16"/><rect x="14" y="4" width="4" height="16"/></svg>
                <span>${dict.pauseTrack || "Pause"}</span>
              ` : `
                <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><polygon points="6 3 20 12 6 21 6 3"/></svg>
                <span>${dict.playTrack || "Play Track"}</span>
              `}
            </button>
            <a href="${song.driveUrl}" target="_blank" rel="noopener" class="btn-song-action" title="Open in Google Drive">
              <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><path d="M19.35 10.04C18.67 6.59 15.64 4 12 4 9.11 4 6.6 5.64 5.35 8.04 2.34 8.36 0 10.91 0 14c0 3.31 2.69 6 6 6h13c2.76 0 5-2.24 5-5 0-2.64-2.05-4.78-4.65-4.96zM17 13l-5 5-5-5h3V9h4v4h3z"/></svg>
              <span>Drive</span>
            </a>
          </div>
        </article>
      `;
    }).join('');
  }

  window.playSong = function(index) {
    if (!SAKHYAM_DATA.songs || !SAKHYAM_DATA.songs[index]) return;

    const song = SAKHYAM_DATA.songs[index];
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const title = song[titleKey] || song.titleEn;

    if (currentPlayingSongIndex === index) {
      toggleSongPlay();
      return;
    }

    currentPlayingSongIndex = index;

    // Update bottom floating audio bar
    const floatingBar = document.getElementById('floating-audio-bar');
    if (floatingBar) {
      floatingBar.classList.add('active');
      const barTitle = document.getElementById('bar-track-title');
      const barArtist = document.getElementById('bar-track-artist');
      const barDrive = document.getElementById('bar-drive-link');
      if (barTitle) barTitle.textContent = `${title} (#${String(song.trackNo).padStart(2, '0')})`;
      if (barArtist) barArtist.textContent = song.artist;
      if (barDrive) barDrive.href = song.driveUrl;
    }

    // Set embedded preview iframe
    const playerIframe = document.getElementById('song-player-iframe');
    if (playerIframe) {
      playerIframe.src = song.previewUrl;
    }

    // Update modal details
    const modalTrackBadge = document.getElementById('modal-track-badge');
    const modalTrackTitle = document.getElementById('modal-track-title');
    const modalTrackArtist = document.getElementById('modal-track-artist');
    const modalBtnDrive = document.getElementById('modal-btn-drive');
    const modalBtnDownload = document.getElementById('modal-btn-download');
    const modalBtnShare = document.getElementById('modal-btn-share');

    if (modalTrackBadge) modalTrackBadge.textContent = `Track #${String(song.trackNo).padStart(2, '0')} • ${song.category}`;
    if (modalTrackTitle) modalTrackTitle.textContent = title;
    if (modalTrackArtist) modalTrackArtist.textContent = `${song.artist} • ${song.collection}`;
    if (modalBtnDrive) modalBtnDrive.href = song.driveUrl;
    if (modalBtnDownload) modalBtnDownload.href = song.streamUrl;
    if (modalBtnShare) modalBtnShare.onclick = () => shareSongWhatsApp(index);

    // Play via HTML5 Audio Engine (Direct Usercontent Stream with Docs fallback)
    const primaryStreamUrl = `https://drive.usercontent.google.com/download?id=${song.fileId}&export=download`;
    audioEngine.src = primaryStreamUrl;
    audioEngine.load();
    const playPromise = audioEngine.play();
    if (playPromise !== undefined) {
      playPromise.catch(err => {
        console.warn('Primary stream playback error, trying fallback:', err);
        audioEngine.src = `https://docs.google.com/uc?export=download&id=${song.fileId}`;
        audioEngine.load();
        audioEngine.play().catch(e => console.warn('Fallback stream error:', e));
      });
    }

    updateAllSongCardButtons();
  };

  function toggleSongPlay() {
    if (currentPlayingSongIndex === -1) {
      if (SAKHYAM_DATA.songs && SAKHYAM_DATA.songs.length > 0) {
        playSong(0);
      }
      return;
    }

    if (audioEngine.paused) {
      audioEngine.play().catch(e => console.warn('Play error:', e));
    } else {
      audioEngine.pause();
    }
  }

  function updateBarPlayPauseIcons(playing) {
    const iconPlay = document.querySelector('.bar-icon-play');
    const iconPause = document.querySelector('.bar-icon-pause');
    if (iconPlay && iconPause) {
      iconPlay.style.display = playing ? 'none' : 'block';
      iconPause.style.display = playing ? 'block' : 'none';
    }
  }

  function updateAllSongCardButtons() {
    const dict = I18N[currentLang] || I18N.en;
    document.querySelectorAll('.song-card').forEach(card => {
      const cardIdx = parseInt(card.getAttribute('data-song-index'));
      const isCurrent = cardIdx === currentPlayingSongIndex;
      const isPlaying = isCurrent && !audioEngine.paused;

      card.classList.toggle('is-playing', isCurrent);
      const playBtn = card.querySelector('.btn-song-play');
      if (playBtn) {
        if (isPlaying) {
          playBtn.innerHTML = `
            <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><rect x="6" y="4" width="4" height="16"/><rect x="14" y="4" width="4" height="16"/></svg>
            <span>${dict.pauseTrack || "Pause"}</span>
          `;
        } else {
          playBtn.innerHTML = `
            <svg viewBox="0 0 24 24" width="14" height="14" fill="currentColor"><polygon points="6 3 20 12 6 21 6 3"/></svg>
            <span>${dict.playTrack || "Play Track"}</span>
          `;
        }
      }
    });
  }

  window.prevSong = function() {
    if (!SAKHYAM_DATA.songs) return;
    let nextIdx = currentPlayingSongIndex - 1;
    if (nextIdx < 0) nextIdx = SAKHYAM_DATA.songs.length - 1;
    playSong(nextIdx);
  };

  window.nextSong = function() {
    if (!SAKHYAM_DATA.songs) return;
    let nextIdx = currentPlayingSongIndex + 1;
    if (nextIdx >= SAKHYAM_DATA.songs.length) nextIdx = 0;
    playSong(nextIdx);
  };

  window.openSongModal = function() {
    const modal = document.getElementById('song-player-modal');
    if (modal) {
      modal.classList.add('open');
      modal.setAttribute('aria-hidden', 'false');
    }
  };

  window.closeSongModal = function() {
    const modal = document.getElementById('song-player-modal');
    if (modal) {
      modal.classList.remove('open');
      modal.setAttribute('aria-hidden', 'true');
    }
  };

  window.shareSongWhatsApp = function(index) {
    if (!SAKHYAM_DATA.songs || !SAKHYAM_DATA.songs[index]) return;
    const song = SAKHYAM_DATA.songs[index];
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    const title = song[titleKey] || song.titleEn;

    const msg = `🎵 *${title}* (Track #${String(song.trackNo).padStart(2, '0')})\n` +
      `Category: ${song.category}\n` +
      `Curator: ${song.artist}\n` +
      `📁 Listen / Download on Google Drive: ${song.driveUrl}\n\n` +
      `Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam`;

    window.open(`https://wa.me/?text=${encodeURIComponent(msg)}`, '_blank', 'noopener');
  };

  window.resetSongSearch = function() {
    activeSongCategory = 'all';
    songSearchQuery = '';
    songsDisplayCount = 12;
    const searchInput = document.getElementById('songs-search-input');
    const clearBtn = document.getElementById('songs-clear-search');
    if (searchInput) searchInput.value = '';
    if (clearBtn) clearBtn.style.display = 'none';

    document.querySelectorAll('.song-filter-btn').forEach(b => {
      b.classList.toggle('active', b.getAttribute('data-song-filter') === 'all');
    });

    renderSongs();
  };

  // Songs Category Filter Buttons
  document.querySelectorAll('.song-filter-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('.song-filter-btn').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      activeSongCategory = btn.getAttribute('data-song-filter');
      songsDisplayCount = 12;
      renderSongs();
    });
  });

  // Songs Search Input & Clear
  const songsSearchInput = document.getElementById('songs-search-input');
  const songsClearSearch = document.getElementById('songs-clear-search');
  if (songsSearchInput) {
    songsSearchInput.addEventListener('input', (e) => {
      songSearchQuery = e.target.value;
      if (songsClearSearch) {
        songsClearSearch.style.display = songSearchQuery ? 'block' : 'none';
      }
      renderSongs();
    });
  }

  if (songsClearSearch) {
    songsClearSearch.addEventListener('click', () => {
      resetSongSearch();
    });
  }

  // Floating Audio Bar Controls
  const barPlayToggle = document.getElementById('bar-play-toggle');
  if (barPlayToggle) {
    barPlayToggle.addEventListener('click', toggleSongPlay);
  }

  const barPrevBtn = document.getElementById('bar-prev-btn');
  if (barPrevBtn) {
    barPrevBtn.addEventListener('click', prevSong);
  }

  const barNextBtn = document.getElementById('bar-next-btn');
  if (barNextBtn) {
    barNextBtn.addEventListener('click', nextSong);
  }

  const barExpandModal = document.getElementById('bar-expand-modal');
  if (barExpandModal) {
    barExpandModal.addEventListener('click', openSongModal);
  }

  const barCloseBtn = document.getElementById('bar-close-btn');
  if (barCloseBtn) {
    barCloseBtn.addEventListener('click', () => {
      const floatingBar = document.getElementById('floating-audio-bar');
      if (floatingBar) {
        floatingBar.classList.remove('active');
      }
    });
  }

  // Modal Close Listeners
  const songModalClose = document.getElementById('song-modal-close');
  if (songModalClose) {
    songModalClose.addEventListener('click', closeSongModal);
  }

  const songPlayerModal = document.getElementById('song-player-modal');
  if (songPlayerModal) {
    songPlayerModal.addEventListener('click', (e) => {
      if (e.target === songPlayerModal) {
        closeSongModal();
      }
    });
  }

  // Progressive Section Hydration (Loads heavy sections part by part on scroll)
  function initProgressiveHydration() {
    if (document.getElementById('songs-grid')) {
      renderSongs();
      return;
    }
    if (document.getElementById('lectionary-items-grid')) {
      renderLectionary();
      return;
    }

    const lazySections = [
      { id: 'lectionary', render: renderUpcomingLectionaryWidget },
      { id: 'songs', render: renderHomeSongsWidget },
      { id: 'achievements', render: renderAchievements },
      { id: 'parishes', render: renderParishes },
      { id: 'leadership', render: renderOfficeBearers },
      { id: 'gallery', render: renderGallery }
    ];

    if ('IntersectionObserver' in window) {
      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            const section = lazySections.find(s => s.id === entry.target.id);
            if (section && typeof section.render === 'function') {
              section.render();
            }
            observer.unobserve(entry.target);
          }
        });
      }, { rootMargin: '300px 0px' });

      lazySections.forEach(s => {
        const el = document.getElementById(s.id);
        if (el) observer.observe(el);
      });
    } else {
      renderUpcomingLectionaryWidget();
      renderHomeSongsWidget();
      renderAchievements();
      renderParishes();
      renderOfficeBearers();
      renderGallery();
    }
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

  // 9. Initial Renders (Part-by-part progressive hydration for instant loading)
  applyTranslations();
  renderPillars();
  renderKalamelaItems();
  initProgressiveHydration();
  initCounters();
  initTiltEffect();
  if (typeof initFlyingLogoScroll === 'function') {
    initFlyingLogoScroll();
  }

  // Auto-scroll to upcoming week on dedicated lectionary page or if hash is #lectionary
  if (document.getElementById('lectionary-items-grid') || window.location.hash === '#lectionary') {
    setTimeout(() => {
      scrollToUpcomingWeek();
    }, 600);
  }
});
