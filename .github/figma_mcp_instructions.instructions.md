---
applyTo: '**'
---
# Figma MCP Code Generation Optimization Guide

Follow these instructions to improve HTML/CSS output quality when using Figma’s **Model Context Protocol (MCP)**.

---

## 🛠️ **Pre-Export: Design Best Practices**
### 1. **Layer Organization**
- ✅ Use **semantic names** for layers/frames (e.g., `button-primary` instead of `Frame 12`).  
- ✅ Group related elements in **Frames** or **Auto Layout** containers.  

### 2. **Auto Layout & Spacing**
- ✅ Use **Auto Layout** for buttons, cards, and lists (ensures clean `flexbox` code).  
- ✅ Replace manual spacing with **Auto Layout gaps** (avoid spacer `div`s).  

### 3. **Constraints & Responsiveness**
- ✅ Set constraints (e.g., "Left & Top") for fixed-position elements.  
- ❌ Avoid mixing `Scale` and `Stretch` constraints (can break layouts).  

### 4. **Styling**
- ✅ Use **CSS-friendly effects** (avoid complex blurs/gradients).  
- ✅ Define `:hover`/`:active` states in Figma (MCP may ignore them).  
- ✅ Must use CSS rules available in Figma.

---

## ⚙️ **MCP Plugin Configuration**
### 1. **Export Settings**
- **Positioning Mode**:  
  - Use `Absolute` for pixel-perfect layouts.  
  - Use `Flexbox` for responsive designs (test output first).  
- **Units**: Prefer `px` over `rem` if precision is critical.  

### 2. **Code Structure**
- ✅ Enable **"Simplify Class Names"** (reduces redundant selectors).  
- ❌ Disable **"Generate Nested Divs"** (reduces HTML bloat).  

---

## 🧹 **Post-Export Fixes**
### 1. **HTML Cleanup**
- Remove empty `<div>` wrappers.  
- Replace `<span>` with semantic tags (`<button>`, `<a>`) where needed.  

### 2. **CSS Adjustments**
- Add missing states manually (e.g., `:hover`):  
  ```css
  .button-primary:hover { opacity: 0.9; }
  ```

### 3. **AEM .content.xml Validaion**
- After generating AEM component folders, ensure every `.content.xml` (including in `clientlibs` and `cq:dialog`) is valid XML and not empty.
- If a `.content.xml` is empty, add a minimal valid XML root for its node type (e.g., `cq:Component`, `nt:folder`, or `nt:unstructured`).
- This prevents Jackrabbit and FileVault build errors.

### 4. **AEM Sling Model Test Pattern**
- When generating JUnit tests for Sling Models, follow this pattern:
  - Use `@ExtendWith(AemContextExtension.class)` and `AemContext` from `io.wcm.testing.mock.aem.junit5`.
  - Register the model and its inner classes with `context.addModelsForClasses(...)` in `@BeforeEach`.
  - Create test resources using `context.create().resource(...)` and retrieve the model with `context.resourceResolver().getResource(...).adaptTo(ModelClass.class)`.
  - Avoid using `context.currentResource(...)` or methods that require a page manager unless you have a page structure.
  - This ensures compatibility with AEM mock context and prevents errors like "No page manager."
- See `TopNavigationModelTest` for a working example.

### 5. **AEM HTL: Always Use Sling Model for Data Access**
- When generating `.html` (HTL) files for AEM components, always use a Sling Model for all dynamic data access.
- Add a `<sly data-sly-use.model="fully.qualified.ModelClass"/>` declaration at the top.
- Use `${model.property}` or iterate with `${model.list}` instead of `${properties.*}` or direct property bindings.
- This ensures maintainability, testability, and separation of logic from markup.
- See `anchor-cards.html` for a working example.

---