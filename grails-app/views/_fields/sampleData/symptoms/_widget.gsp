<g:select from="${rosalind.Symptom.list()}"
	  multiple="true"
          value="${sampleData?.symptoms*.id}"
	  name="symptoms"
	  optionKey="id"
/>
