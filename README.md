# Lucas Carpenter - Personal Landing Page

A modern, responsive personal website featuring portfolio sections, client galleries, and interactive contact forms.

## 🗂️ Project Structure

```
📁 LandingPage/
├── 📄 index.html                    # Main landing page
├── 📄 README.md                     # This file
├── 📁 assets/                       # Static assets
│   ├── 📁 css/                     
│   │   └── main.css                 # Main stylesheet
│   ├── 📁 js/                       # JavaScript files (future)
│   ├── 📁 images/                   # Image assets
│   │   └── 📁 lucas/                # Lucas's personal photos
│   └── 📁 fonts/                    # Custom fonts (future)
├── 📁 pages/                        # All HTML pages
│   ├── 📁 directories/              # Directory landing pages
│   │   ├── creative-work.html       # Creative work directory
│   │   ├── programming-tech.html    # Programming & tech directory
│   │   ├── content-communication.html # Content & communication directory
│   │   ├── personal-life.html       # Personal & life directory
│   │   └── under-construction.html  # Under construction page
│   ├── 📁 client-galleries/         # Photography client system
│   │   ├── photographyLucasCLIENTGALLERIES_SECURITY.html # Client access
│   │   └── gallery.html             # Individual gallery page
│   ├── 📁 admin/                    # Admin pages (future)
│   ├── photographyLucas.html        # Main photography portfolio
│   ├── contact.html                 # Contact page
│   ├── about.html                   # About page
│   └── portfolio.html               # Portfolio page
├── 📁 data/                         # Data files
│   └── clients.csv                  # Client gallery data
├── 📁 php/                          # Server-side scripts
│   └── contact.php                  # Contact form handler
├── 📁 client-galleries/             # Client gallery files
└── 📁 src/                          # Source files (development)
```

## 🚀 Features

### Main Landing Page
- **Hero Section** with animated background
- **About Card** with professional introduction
- **Directory System** organized into 4 main categories:
  - Creative Work (Photography, Design, Video, Art)
  - Programming & Technology (Web Dev, Mobile, Open Source, AI/ML)
  - Content & Communication (Writing, Speaking, Podcasts, Tutorials)
  - Personal & Life (Travel, Timeline, Events, Identity)
- **Contact Form** with PHP backend
- **Responsive Design** for all devices

### Client Gallery System
- **Secure client access** with password protection
- **Bulk photo download** functionality
- **Mobile device warnings** for optimal experience
- **CSV-based client management**

### Visual Features
- **Glass morphism** design elements
- **Floating particles** animation
- **Directory card animations** with hover effects
- **Unavailable banners** for incomplete sections
- **Professional typography** with Google Fonts

## 🛠️ Technical Stack

- **Frontend**: HTML5, CSS3, JavaScript (ES6+)
- **Backend**: PHP (contact form)
- **Styling**: Custom CSS with animations and responsive design
- **Data**: CSV files for client management
- **Fonts**: Google Fonts (Montserrat, Roboto)

## 📁 File Organization Guidelines

### Adding New Pages
1. **Directory Pages**: Add to `pages/directories/`
2. **Client Features**: Add to `pages/client-galleries/`
3. **Admin Tools**: Add to `pages/admin/`
4. **General Pages**: Add to `pages/`

### Adding Assets
1. **Stylesheets**: Add to `assets/css/`
2. **JavaScript**: Add to `assets/js/`
3. **Images**: Add to `assets/images/` (create subfolders by category)
4. **Fonts**: Add to `assets/fonts/`

### Adding Functionality
1. **PHP Scripts**: Add to `php/`
2. **Data Files**: Add to `data/`
3. **Development Files**: Add to `src/`

## 🔗 Path Updates Needed

After reorganization, update these file paths:

### In index.html
```html
<!-- Update CSS path -->
<link rel="stylesheet" href="assets/css/main.css">

<!-- Update image paths -->
<img src="assets/images/lucas/indexMain.jpg" alt="Lucas Carpenter avatar">

<!-- Update page links -->
<a href="pages/photographyLucas.html">
<a href="pages/directories/under-construction.html">
<!-- etc. -->
```

### In contact form
```html
<!-- Update PHP action -->
<form action="php/contact.php" method="POST">
```

## 🎯 Future Expansion Areas

### Content Directories
- **Design Portfolio**: UI/UX projects, graphics, branding
- **Video Gallery**: Cinematography reel, edited projects
- **Writing Blog**: Technical articles, creative writing
- **Travel Journal**: Photo stories, travel guides
- **Project Showcase**: Detailed case studies

### Technical Enhancements
- **CMS Integration**: WordPress or headless CMS
- **Database Migration**: Move from CSV to proper database
- **User Authentication**: Secure client login system
- **Analytics Integration**: Google Analytics, heatmaps
- **SEO Optimization**: Meta tags, structured data
- **Performance**: Image optimization, caching

### Features to Add
- **Search Functionality**: Site-wide search
- **Blog System**: Dynamic content management
- **Gallery Lightbox**: Enhanced photo viewing
- **Social Media Integration**: Instagram feed, Twitter
- **Newsletter Signup**: Email marketing integration

## 📋 Development Workflow

### Adding a New Directory Category
1. Create HTML page in `pages/directories/`
2. Add card to `index.html` directory section
3. Update navigation links
4. Add category-specific styling if needed
5. Test responsive design

### Adding Client Gallery
1. Add client data to `data/clients.csv`
2. Upload photos to appropriate folder
3. Test gallery access and download functionality
4. Verify mobile responsiveness

### Updating Styles
1. Edit `assets/css/main.css`
2. Test across all pages
3. Verify responsive breakpoints
4. Check browser compatibility

## 🚀 Deployment

1. **Development**: Test locally with all file paths
2. **Staging**: Upload to test server, verify all links
3. **Production**: Deploy to live server
4. **Post-Deploy**: Test contact form, gallery system

## 📞 Maintenance

### Regular Tasks
- Update client galleries as needed
- Add new portfolio pieces
- Refresh content in directories
- Monitor contact form submissions
- Update personal photos/bio

### Performance Monitoring
- Check page load speeds
- Monitor mobile experience
- Test contact form functionality
- Verify gallery download system

---

**Built with ❤️ by Lucas Carpenter**  
*Last Updated: August 2025*
