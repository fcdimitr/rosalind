<g:select from="${rosalind.Medication.list()}"
	  multiple="true"
          value="${sampleData?.medications*.id}"
	  name="medications"
	  optionKey="id"
/>
