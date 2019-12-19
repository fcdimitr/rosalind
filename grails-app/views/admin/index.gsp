<!doctype html>
<html>
  <g:set var="myTitle" value="Administration panel" />
  <head>
    <meta name="layout" content="main"/>
    <title>${myTitle}</title>
  </head>
  <body>

    <div id="content" role="main">
      <section class="row colset-2-its">
        <h1>${myTitle}</h1>

        <div id="controllers" role="navigation">

          <ul>

            <li class="controller">
              <a href="/sampleData/index">Sample demographics</a>
            </li>

            <li class="controller">
              <a href="/sampleProvider/index">List of providers</a>
            </li>
            
            <li class="controller">
              <a href="/method/index">Experimental methods</a>
            </li>

            <li class="controller">
              <a href="/biomarker/index">Biomarkers</a>
            </li>

            <li class="controller">
              <a href="/experiment/index">Experiments</a>
            </li>

            <li class="controller">
              <a href="/dataRequest/adminGetData">Data extraction</a>
            </li>

            <!-- space -->
            <p>&nbsp;</p>

            <li class="controller">
              <a href="/dataRequest/index">Data requests</a>
            </li>
            
            <li class="controller">
              <a href="/user/index">User accounts management</a>
            </li>

            <!-- space -->
            <p>&nbsp;</p>
            
            <li class="controller">
              <a href="/publication/index">Publications</a>
            </li>

            <!-- space -->
            <p>&nbsp;</p>

            <li class="controller">
              <a href="/experimentType/index">Experiment types</a>
            </li>

            <li class="controller">
              <a href="/exposure/index">Chemical exposures</a>
            </li>

            <li class="controller">
              <a href="/cohort/index">Cohort types</a>
            </li>

            <li class="controller">
              <a href="/medication/index">Medication types</a>
            </li>

            <li class="controller">
              <a href="/symptom/index">Symptom list</a>
            </li>

            <li class="controller">
              <a href="/publicationType/index">Publication types</a>
            </li>

            <li class="controller">
              <a href="/educationLevel/index">Education levels</a>
            </li>

            <li class="controller">
              <a href="/race/index">Race categories</a>
            </li>

            <li class="controller">
              <a href="/frontEndField/index">Homepage items</a>
            </li>

            <li class="controller">
              <a href="/image/index">Web images</a>
            </li>
            
          </ul>

        </div>
      </section>
    </div>

  </body>
</html>
