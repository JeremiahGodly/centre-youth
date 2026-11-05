// Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam
// Motion Graphics, Interactive Canvas, Parallax Tilt & Lightbox

class CanvasAura {
  constructor(canvasId) {
    this.canvas = document.getElementById(canvasId);
    if (!this.canvas) return;
    this.ctx = this.canvas.getContext('2d');
    this.particles = [];
    this.particleCount = window.innerWidth < 768 ? 35 : 70;
    this.mouse = { x: null, y: null, radius: 140 };
    this.animationFrame = null;
    this.theme = document.documentElement.getAttribute('data-theme') || 'dark';

    this.init();
    this.bindEvents();
    this.animate();
  }

  init() {
    this.resize();
    this.particles = [];
    for (let i = 0; i < this.particleCount; i++) {
      this.particles.push(new Particle(this.canvas.width, this.canvas.height, this.theme));
    }
  }

  resize() {
    this.canvas.width = this.canvas.parentElement.offsetWidth || window.innerWidth;
    this.canvas.height = this.canvas.parentElement.offsetHeight || 650;
  }

  updateTheme(theme) {
    this.theme = theme;
    this.particles.forEach(p => p.setTheme(theme));
  }

  bindEvents() {
    window.addEventListener('resize', () => {
      this.resize();
    });

    window.addEventListener('mousemove', (e) => {
      const rect = this.canvas.getBoundingClientRect();
      this.mouse.x = e.clientX - rect.left;
      this.mouse.y = e.clientY - rect.top;
    });

    window.addEventListener('mouseleave', () => {
      this.mouse.x = null;
      this.mouse.y = null;
    });

    window.addEventListener('touchmove', (e) => {
      if (e.touches.length > 0) {
        const rect = this.canvas.getBoundingClientRect();
        this.mouse.x = e.touches[0].clientX - rect.left;
        this.mouse.y = e.touches[0].clientY - rect.top;
      }
    }, { passive: true });
  }

  animate() {
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

    // Draw gentle connecting lines
    for (let a = 0; a < this.particles.length; a++) {
      for (let b = a + 1; b < this.particles.length; b++) {
        const dx = this.particles[a].x - this.particles[b].x;
        const dy = this.particles[a].y - this.particles[b].y;
        const dist = Math.hypot(dx, dy);

        if (dist < 110) {
          const alpha = (1 - dist / 110) * (this.theme === 'dark' ? 0.22 : 0.15);
          this.ctx.strokeStyle = this.theme === 'dark'
            ? `rgba(245, 158, 11, ${alpha})`
            : `rgba(2, 132, 199, ${alpha})`;
          this.ctx.lineWidth = 0.8;
          this.ctx.beginPath();
          this.ctx.moveTo(this.particles[a].x, this.particles[a].y);
          this.ctx.lineTo(this.particles[b].x, this.particles[b].y);
          this.ctx.stroke();
        }
      }
    }

    // Update & draw particles
    this.particles.forEach(p => {
      p.update(this.canvas.width, this.canvas.height, this.mouse);
      p.draw(this.ctx);
    });

    this.animationFrame = requestAnimationFrame(() => this.animate());
  }
}

class Particle {
  constructor(w, h, theme) {
    this.x = Math.random() * w;
    this.y = Math.random() * h;
    this.size = Math.random() * 2.8 + 1.2;
    this.baseX = this.x;
    this.baseY = this.y;
    this.vx = (Math.random() - 0.5) * 0.9;
    this.vy = (Math.random() - 0.5) * 0.9;
    this.density = Math.random() * 20 + 5;
    this.setTheme(theme);
  }

  setTheme(theme) {
    this.theme = theme;
    const colorsDark = [
      'rgba(245, 158, 11, 0.75)',   // Amber gold
      'rgba(56, 189, 248, 0.7)',   // Sky blue
      'rgba(225, 29, 72, 0.65)',   // Rose
      'rgba(255, 255, 255, 0.8)'   // Pure light
    ];
    const colorsLight = [
      'rgba(217, 119, 6, 0.6)',    // Deep gold
      'rgba(2, 132, 199, 0.65)',   // Royal ocean
      'rgba(190, 18, 60, 0.55)',   // Ruby red
      'rgba(15, 23, 42, 0.45)'     // Slate
    ];
    const palette = theme === 'dark' ? colorsDark : colorsLight;
    this.color = palette[Math.floor(Math.random() * palette.length)];
  }

  update(w, h, mouse) {
    this.x += this.vx;
    this.y += this.vy;

    // Bounce off edges
    if (this.x < 0 || this.x > w) this.vx = -this.vx;
    if (this.y < 0 || this.y > h) this.vy = -this.vy;

    // Mouse push effect
    if (mouse.x !== null && mouse.y !== null) {
      const dx = mouse.x - this.x;
      const dy = mouse.y - this.y;
      const distance = Math.hypot(dx, dy);

      if (distance < mouse.radius) {
        const forceDirectionX = dx / distance;
        const forceDirectionY = dy / distance;
        const force = (mouse.radius - distance) / mouse.radius;
        const directionX = forceDirectionX * force * (this.density / 3);
        const directionY = forceDirectionY * force * (this.density / 3);
        this.x -= directionX;
        this.y -= directionY;
      }
    }
  }

  draw(ctx) {
    ctx.fillStyle = this.color;
    ctx.beginPath();
    ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
    ctx.closePath();
    ctx.fill();
  }
}

// 3D Tilt Card Interaction
function initTiltEffect() {
  const cards = document.querySelectorAll('.tilt-card');
  cards.forEach(card => {
    card.addEventListener('mousemove', (e) => {
      const rect = card.getBoundingClientRect();
      const x = e.clientX - rect.left;
      const y = e.clientY - rect.top;
      const centerX = rect.width / 2;
      const centerY = rect.height / 2;
      const rotateX = ((y - centerY) / centerY) * -7;
      const rotateY = ((x - centerX) / centerX) * 7;

      card.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) scale3d(1.02, 1.02, 1.02)`;
    });

    card.addEventListener('mouseleave', () => {
      card.style.transform = `perspective(1000px) rotateX(0deg) rotateY(0deg) scale3d(1, 1, 1)`;
    });
  });
}

// Interactive Audio Player with Equalizer Waveform
class AudioPlayerVisualizer {
  constructor(audioUrl, btnId, canvasId) {
    this.audio = new Audio(audioUrl);
    this.btn = document.getElementById(btnId);
    this.canvas = document.getElementById(canvasId);
    if (!this.btn || !this.canvas) return;

    this.ctx = this.canvas.getContext('2d');
    this.isPlaying = false;
    this.bars = 32;
    this.barValues = Array(this.bars).fill(6);
    this.animId = null;

    this.bindEvents();
    this.drawIdle();
  }

  bindEvents() {
    this.btn.addEventListener('click', () => {
      if (this.isPlaying) {
        this.pause();
      } else {
        this.play();
      }
    });

    this.audio.addEventListener('ended', () => {
      this.pause();
    });
  }

  play() {
    this.audio.play().then(() => {
      this.isPlaying = true;
      this.btn.classList.add('playing');
      const currentLang = document.documentElement.getAttribute('data-lang') || 'en';
      const pauseLabel = I18N[currentLang]?.audioPause || "Pause Audio";
      this.btn.querySelector('.audio-btn-text').textContent = pauseLabel;
      this.animate();
    }).catch(e => {
      console.log("Audio autoplay / playback notice:", e);
      // Simulate playback visualizer anyway if browser blocked unmuted autoplay
      this.isPlaying = true;
      this.btn.classList.add('playing');
      this.animate();
    });
  }

  pause() {
    this.audio.pause();
    this.isPlaying = false;
    this.btn.classList.remove('playing');
    const currentLang = document.documentElement.getAttribute('data-lang') || 'en';
    const playLabel = I18N[currentLang]?.audioPlay || "Play Audio Snippet";
    this.btn.querySelector('.audio-btn-text').textContent = playLabel;
    cancelAnimationFrame(this.animId);
    this.drawIdle();
  }

  animate() {
    if (!this.isPlaying) return;
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

    const barWidth = this.canvas.width / this.bars;
    const isDark = document.documentElement.getAttribute('data-theme') === 'dark';

    for (let i = 0; i < this.bars; i++) {
      // Dynamic bouncing height
      this.barValues[i] = Math.random() * (this.canvas.height - 8) + 6;
      const h = this.barValues[i];
      const x = i * barWidth;
      const y = (this.canvas.height - h) / 2;

      const grad = this.ctx.createLinearGradient(0, y, 0, y + h);
      if (isDark) {
        grad.addColorStop(0, '#f59e0b');
        grad.addColorStop(1, '#06b6d4');
      } else {
        grad.addColorStop(0, '#0284c7');
        grad.addColorStop(1, '#d97706');
      }

      this.ctx.fillStyle = grad;
      this.ctx.fillRect(x + 1.5, y, barWidth - 3, h);
    }

    this.animId = requestAnimationFrame(() => this.animate());
  }

  drawIdle() {
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    const barWidth = this.canvas.width / this.bars;
    const isDark = document.documentElement.getAttribute('data-theme') === 'dark';

    for (let i = 0; i < this.bars; i++) {
      const h = 6 + Math.sin(i * 0.4) * 4;
      const x = i * barWidth;
      const y = (this.canvas.height - h) / 2;

      this.ctx.fillStyle = isDark ? 'rgba(255, 255, 255, 0.25)' : 'rgba(15, 23, 42, 0.25)';
      this.ctx.fillRect(x + 1.5, y, barWidth - 3, h);
    }
  }
}

// Lightbox Modal Controller
class Lightbox {
  constructor() {
    this.modal = document.getElementById('lightbox-modal');
    if (!this.modal) return;
    this.img = document.getElementById('lightbox-img');
    this.caption = document.getElementById('lightbox-caption');
    this.closeBtn = document.getElementById('lightbox-close');
    this.prevBtn = document.getElementById('lightbox-prev');
    this.nextBtn = document.getElementById('lightbox-next');
    this.currentList = [];
    this.currentIndex = 0;

    this.bindEvents();
  }

  bindEvents() {
    this.closeBtn.addEventListener('click', () => this.close());
    this.modal.addEventListener('click', (e) => {
      if (e.target === this.modal) this.close();
    });

    if (this.prevBtn) {
      this.prevBtn.addEventListener('click', () => this.step(-1));
    }
    if (this.nextBtn) {
      this.nextBtn.addEventListener('click', () => this.step(1));
    }

    document.addEventListener('keydown', (e) => {
      if (!this.modal.classList.contains('active')) return;
      if (e.key === 'Escape') this.close();
      if (e.key === 'ArrowLeft') this.step(-1);
      if (e.key === 'ArrowRight') this.step(1);
    });
  }

  open(list, index) {
    this.currentList = list;
    this.currentIndex = index;
    this.update();
    this.modal.classList.add('active');
    document.body.style.overflow = 'hidden';
  }

  close() {
    this.modal.classList.remove('active');
    document.body.style.overflow = '';
  }

  step(direction) {
    if (this.currentList.length <= 1) return;
    this.currentIndex = (this.currentIndex + direction + this.currentList.length) % this.currentList.length;
    this.update();
  }

  update() {
    const item = this.currentList[this.currentIndex];
    if (!item) return;
    this.img.src = item.src;
    const currentLang = document.documentElement.getAttribute('data-lang') || 'en';
    const titleKey = currentLang === 'ml' ? 'titleMl' : (currentLang === 'mang' ? 'titleMang' : 'titleEn');
    this.caption.textContent = item[titleKey] || item.titleEn;
  }
}

// 3D Flying Logo & Synchronous Spotlight Scroll Controller
function initFlyingLogoScroll() {
  const logoBox = document.getElementById('flying-logo-element');
  const section = document.getElementById('emblem-3d');
  const logoImg = document.getElementById('flying-logo-img');
  if (!logoBox || !section) return;

  let currentProgress = 0.5;
  let targetProgress = 0.5;
  let currentAngle = 0;
  let targetAngle = 0;
  let isVisible = false;
  let idleTime = 0;

  // Use IntersectionObserver to only compute when section is in or near viewport
  if ('IntersectionObserver' in window) {
    const observer = new IntersectionObserver((entries) => {
      isVisible = entries[0].isIntersecting;
    }, { rootMargin: '120px 0px 120px 0px', threshold: 0 });
    observer.observe(section);
  } else {
    isVisible = true;
  }

  function computeScrollTargets() {
    const rect = section.getBoundingClientRect();
    const windowHeight = window.innerHeight;

    // Calculate normalized progress as section travels through viewport
    // 0 = section entering bottom, 0.5 = centered, 1 = leaving top
    const rawProgress = (windowHeight - rect.top) / (windowHeight + rect.height);
    targetProgress = Math.max(0, Math.min(1, rawProgress));

    // Continuous 360-degree rotation of spotlight half-circle synced directly with scroll
    // Starts at -45deg and rotates smoothly to +315deg
    targetAngle = -45 + targetProgress * 360;
  }

  window.addEventListener('scroll', computeScrollTargets, { passive: true });
  window.addEventListener('resize', computeScrollTargets);
  computeScrollTargets();
  currentProgress = targetProgress;
  currentAngle = targetAngle;

  function renderLoop() {
    if (isVisible) {
      // Smooth linear interpolation (lerp) eliminates discrete stepping
      const ease = 0.085;
      currentProgress += (targetProgress - currentProgress) * ease;
      currentAngle += (targetAngle - currentAngle) * ease;

      idleTime += 0.02;
      const subtleBreathe = Math.sin(idleTime) * 1.5;

      // 3D rotation angles smoothly driven by scroll progress
      const rotateY = (currentProgress - 0.5) * 34 + subtleBreathe;
      const rotateX = Math.sin(currentProgress * Math.PI) * 12 + Math.cos(idleTime) * 1.2;
      const translateY = (1 - Math.sin(currentProgress * Math.PI)) * -28;
      const scale = 0.94 + Math.sin(currentProgress * Math.PI) * 0.12;

      // Dynamic spotlight half-circle angle
      logoBox.style.setProperty('--spotlight-angle', `${currentAngle.toFixed(2)}deg`);

      // Dynamic cast shadow: moves in opposite direction of spotlight
      // Spotlight at angle θ casts shadow at θ + 180°
      const shadowRad = (currentAngle + 180) * (Math.PI / 180);
      const shadowDist = 26;
      const shadowX = (Math.cos(shadowRad) * shadowDist).toFixed(1);
      const shadowY = (Math.sin(shadowRad) * shadowDist + 14).toFixed(1);
      const shadowBlur = (35 + Math.sin(currentProgress * Math.PI) * 15).toFixed(1);

      logoBox.style.boxShadow = `${shadowX}px ${shadowY}px ${shadowBlur}px rgba(0, 0, 0, 0.55), 0 0 35px rgba(245, 158, 11, 0.25)`;

      if (logoImg) {
        logoImg.style.filter = `drop-shadow(${(shadowX * 0.55).toFixed(1)}px ${(shadowY * 0.55).toFixed(1)}px 18px rgba(0, 0, 0, 0.6))`;
      }

      logoBox.style.transform = `perspective(1000px) translateY(${translateY.toFixed(1)}px) rotateY(${rotateY.toFixed(2)}deg) rotateX(${rotateX.toFixed(2)}deg) scale3d(${scale.toFixed(3)}, ${scale.toFixed(3)}, ${scale.toFixed(3)})`;
    }

    requestAnimationFrame(renderLoop);
  }

  requestAnimationFrame(renderLoop);
}
