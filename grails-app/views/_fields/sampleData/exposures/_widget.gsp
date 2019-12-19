<g:select from="${rosalind.Exposure.list()}"
	  multiple="true"
          value="${sampleData?.exposures*.id}"
	  name="exposures"
	  optionKey="id"
/>
