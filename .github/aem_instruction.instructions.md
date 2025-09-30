# ✅ AEM Component Generation Instructions

This document describes the complete process to generate a new AEM component from Figma designs, including prototype generation, dialog setup, Sling Model, and clientlibs.

---

## 📋 **AEM Component Generation Process Summary**

I will guide you through the complete AEM component generation process with these steps:

1. **Component Name** - Get the component name
2. **Desktop Design** - Request Figma link and screenshot for desktop
3. **Desktop Prototype** - Generate and confirm desktop prototype
4. **Mobile Design** - Request Figma link and screenshot for mobile
5. **Mobile Prototype** - Generate and confirm responsive prototype
6. **AEM Generation** - Confirm proceeding with AEM component structure
7. **Dialog Requirements** - Gather dialog field specifications
8. **Build Validation** - Ensure everything works correctly

### **Step 1: Component Name**
**What is the component name?**

### **Step 2: Desktop Design**
**Please share the Figma link for the *desktop design*.**  
**Can you also provide a screenshot of the desktop component as a reference?**

### **Step 3: Desktop Prototype Confirmation**
**Here is the static HTML prototype for desktop (CSS and JS in `/prototype/<component-name>/`, BEM class naming, generated via Figma MCP). Is this okay?**

### **Step 4: Mobile Design**
**Please share the Figma link for the *mobile design* (default breakpoint 640px).**  
**Can you also provide a screenshot of the mobile component as a reference?**

### **Step 5: Mobile Prototype Confirmation**
**Here is the updated prototype with both desktop and mobile styling (CSS and JS in `/prototype/<component-name>/`, media queries working, BEM class naming, generated via Figma MCP). Is this okay?**

### **Step 6: AEM Component Generation Confirmation**
**Do you want me to proceed with generating the AEM component structure now?**

### **Step 7: Dialog Requirements**
**Do you have any dialog requirements for this component? If yes, please specify the fields and types.**

### **Step 8: Final Validation**
**Validate if the dialog is opening correctly?**

### **Step 9: Completion Confirmation**
**AEM component generation is complete, the build is passing, and test coverage is ≥ 90%.**

---

## 📌 Step 1 – Generate Static Prototype from Figma MCP (Desktop)

- Create a standalone prototype under `/prototype/<component-name>/`:
  - **<component-name>.html**
    - Reference CSS and JS from the same folder.
    - Include **inline SVGs** (`<svg>` with `<path>` and `fill` attributes) — **never use `<img>` for SVGs**.
    - Add `<meta name="viewport" content="width=device-width, initial-scale=1.0">` so media queries work.
  - **<component-name>.css**
    - Use **BEM class naming**.
    - Include exact styles from Figma MCP output.
    - Include **media queries** for responsiveness.
  - **<component-name>.js**
    - Include interactivity logic if needed.

✅ The prototype must work **independently** when opened in a browser.

**After generation, ask:**
> Here is the static HTML prototype for desktop (CSS and JS in `/prototype/<component-name>/`, BEM class naming, generated via Figma MCP). Is this okay?

👉 Wait for confirmation.

---

## 📌 Step 2 – Ask for Mobile Design

**Only after desktop prototype confirmation:**

> Please share the Figma link for the *mobile design*.  
> The default breakpoint is `640px` — anything below 640px is mobile, and anything above is desktop.  
> Can you also provide a screenshot of the mobile component as a reference?

👉 Wait for both the mobile Figma link and the screenshot.

---

## 📌 Step 3 – Generate Mobile Responsive Prototype

Using the mobile Figma link and screenshot:

- Update the prototype in the same folder:
  - Add `@media (max-width: 640px)` responsive styles in **<component-name>.css**.
  - Maintain BEM class naming and inline SVG usage.
  - Keep HTML and JS references consistent.

**After generation, ask:**
> Here is the updated prototype with both desktop and mobile styling (CSS and JS in `/prototype/<component-name>/`, media queries working, BEM class naming, generated via Figma MCP). Is this okay?

👉 Wait for confirmation.

---

## 📌 Step 4 – Confirm AEM Component Generation

**Only after both desktop and mobile prototypes are confirmed:**

> Do you want me to proceed with generating the AEM component structure now?

👉 Proceed only if confirmed.

---

## 📌 Step 5 – Generate AEM Component Structure and Ask for Dialog Requirements
> Do you have any dialog requirements for this component? If yes, please specify the fields and types.

👉 Once fields are provided:
- Update `cq:dialog/.content.xml` accordingly.
- Generate a **Sling Model** (`@Model`, `@Inject`).
- Generate a **JUnit Test Class** (≥ 90% coverage).


### ✅ Clientlibs
- Must live under the component folder.
- `.content.xml` for clientlib:
- `css.txt` and `js.txt` reference the generated CSS and JS.
- CSS and JS files should have the code in the component itself to render component correctly.

### ✅ HTL
- Create `<component-name>.html` and reference the clientlib category `accenturesong.base`.
- Follow HTL best practices (use `data-sly-*`, no scriptlets).


### ✅ Dialog
- Create `cq:dialog/.content.xml` using **Granite UI patterns** (Cloud Service compatible):
  - All fields inside a `granite/ui/components/coral/foundation/container`.
  - Use **composite multifield** with `composite="{Boolean}true"`.
  - Use only **Granite UI resource types**:
    - `granite/ui/components/coral/foundation/form/textfield`
    - `granite/ui/components/coral/foundation/form/richtext`
    - etc.
  - No deprecated properties.


---

## 📌 Step 6 – Validate Build

After all files are generated:
- Run `mvn clean install`.
- Save the output.
- If build fails: fix issues and rerun until successful.
- Ensure **test coverage ≥ 90%**.
- Verify that the dialog opens in AEM as a Cloud Service.

**Ask:**
> Validate if the dialog is opening correctly?

👉 If not, refactor dialog structure.

✅ Finally:
> AEM component generation is complete, the build is passing, and test coverage is ≥ 90%.

---

## 📋 Additional Guidance

- Use `sample_dialog.xml` as reference for dialog structure.
- Use `TopNavigationModelTest.java` as reference for Sling Model tests.
- Always follow AEM Maven project structure.
- Always connect to Figma MCP for both desktop and mobile designs.
- Always use **BEM class naming** and **inline SVGs**.
- Always include `<meta name="viewport" ...>` in prototypes.
- Component should be created in apps module under `ui.apps/src/main/content/jcr_root/apps/accenturesong/components`
- Sling Model should be created in core module : core/src/main/java/com/accenture/song/asset/core/models and accordingly test also. Take reference of test from TopNavigationModel test
## AEM Dialog Reference XML to use
```xml
<?xml version="1.0" encoding="UTF-8"?>
<jcr:root
    xmlns:sling="http://sling.apache.org/jcr/sling/1.0"
    xmlns:cq="http://www.day.com/jcr/cq/1.0"
    xmlns:jcr="http://www.jcp.org/jcr/1.0"
    xmlns:nt="http://www.jcp.org/jcr/nt/1.0"
    jcr:primaryType="nt:unstructured"
    jcr:title="Feature Cards"
    sling:resourceType="cq/gui/components/authoring/dialog">

    <content
        jcr:primaryType="nt:unstructured"
        sling:resourceType="granite/ui/components/coral/foundation/fixedcolumns">
        <items jcr:primaryType="nt:unstructured">
            <column
                jcr:primaryType="nt:unstructured"
                sling:resourceType="granite/ui/components/coral/foundation/container">
                <items jcr:primaryType="nt:unstructured">

                    <!-- Simple text fields -->
                    <id
                        jcr:primaryType="nt:unstructured"
                        sling:resourceType="granite/ui/components/coral/foundation/form/textfield"
                        fieldLabel="ID"
                        name="./id"/>

                    <heading
                        jcr:primaryType="nt:unstructured"
                        sling:resourceType="granite/ui/components/coral/foundation/form/textfield"
                        fieldLabel="Heading"
                        name="./heading"/>

                    <subheading
                        jcr:primaryType="nt:unstructured"
                        sling:resourceType="granite/ui/components/coral/foundation/form/richtext"
                        fieldLabel="Subheading"
                        name="./subheading"/>

                    <!-- ✅ Composite Multifield -->
                    <cards
                        jcr:primaryType="nt:unstructured"
                        sling:resourceType="granite/ui/components/coral/foundation/form/multifield"
                        fieldLabel="Cards"
                        composite="{Boolean}true">
                        <field
                            jcr:primaryType="nt:unstructured"
                            sling:resourceType="granite/ui/components/coral/foundation/container"
                            name="./cards">
                            <items jcr:primaryType="nt:unstructured">

                                <iconName
                                    jcr:primaryType="nt:unstructured"
                                    sling:resourceType="granite/ui/components/coral/foundation/form/textfield"
                                    fieldLabel="Icon Name"
                                    name="./iconName"/>

                                <title
                                    jcr:primaryType="nt:unstructured"
                                    sling:resourceType="granite/ui/components/coral/foundation/form/textfield"
                                    fieldLabel="Title"
                                    name="./title"/>

                                <description
                                    jcr:primaryType="nt:unstructured"
                                    sling:resourceType="cq/gui/components/authoring/dialog/richtext"
                                    fieldLabel="Description"
                                    name="./description"
                                    useFixedInlineToolbar="{Boolean}true">
                                    <uiSettings
                                            jcr:primaryType="nt:unstructured"
                                            sling:resourceSuperType="/apps/accenturesong/components/commons/rtePlugins/dialog/uiSettings"/>
                                        <rtePlugins
                                            jcr:primaryType="nt:unstructured"
                                            sling:resourceSuperType="/apps/accenturesong/components/commons/rtePlugins/dialog/rtePlugins"/>
                                    </description>

                            </items>
                        </field>
                    </cards>
                    <!-- ✅ End of composite multifield -->

                </items>
            </column>
        </items>
    </content>
</jcr:root>
```

## AEM Component Structure
Create this folder structure in `ui.apps/src/main/content/jcr_root/apps/<project>/components/<component-name>/`:

```
<component-name>/
  ├── .content.xml                 (jcr:primaryType="cq:Component")
  ├── <component-name>.html        (HTL file)
  ├── cq:dialog/
  │    └── .content.xml            (Granite UI dialog)
  └── clientlibs/
       ├── .content.xml            (clientlib definition)
       ├── css.txt
       ├── js.txt
       ├── <component-name>.css
       └── <component-name>.js
```

## Clientlibs Structure
```xml
<jcr:root
    jcr:primaryType="cq:ClientLibraryFolder"
    categories="[accenturesong.base]"
    allowProxy="{Boolean}true"/>
```

## Sling Model Structure
- Create in core module under `core/src/main/java/com/accenture/song/asset/core/models/`
- Use TopNavigationModel as a reference for Sling Model.
- Use TopNavigationModelTest as a reference for Sling Model tests. Use same libraries and annotations.

## Figma MCP Instructions
- When Figma Frame Link is provided, use Figma MCP Server to downlooad HTML/CSS and images from Figma to generate code.

---

### ✅ Expected Copilot Flow

**ALWAYS START EVERY NEW CHAT WITH THE COMPLETE SUMMARY:**
Show the complete "📋 AEM Component Generation Process Summary" with all 8 steps listed clearly before asking for the component name.

1. **Ask:**
   > What is the component name?

2. **After name:**
   > Please share the Figma link for the *desktop design*.
   > Can you also provide a screenshot of the desktop component as a reference?

3. **After desktop link & screenshot:**
   ✅ Download from Figma with Figma MCP Server → Generate prototype in `/prototype/<component-name>/` with BEM and inline SVGs →  
   > Here is the static HTML prototype for desktop (CSS & JS separate). Is this okay?

4. **After desktop confirmation:**
   > Please share the Figma link for the *mobile design* (default breakpoint 640px).
   > Can you also provide a screenshot of the mobile component as a reference?

5. **After mobile link & screenshot:**
   ✅ Connect to Figma MCP → Update prototype in same folder with media queries →  
   > Here is the updated prototype with both desktop and mobile styling. Is this okay?

6. **After mobile confirmation:**
   > Do you want me to proceed with generating the AEM component structure now?

7. **After confirmation:**
   ✅ Generate HTL and clientlibs (category `test.base`) →  
   > Do you have any dialog requirements for this component?

8. **After dialog requirements:**
   ✅ Generate `cq:dialog/.content.xml` using Granite UI patterns with composite multifield →  
   ✅ Generate Sling Model and JUnit test.

9. **Run build, fix issues if any, ensure ≥ 90% coverage, and confirm success:**
   > Validate if the dialog is opening correctly?

10. **After dialog validation:**
    > AEM component generation is complete, the build is passing, and test coverage is ≥ 90%.