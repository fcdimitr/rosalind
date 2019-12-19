<g:select from="${rosalind.ExperimentType.list()}"
          value="${method?.experiment?.id}"
	  name="experiment"
	  optionKey="id"
          required=""
          noSelection="${['':'Select one...']}"
/>
