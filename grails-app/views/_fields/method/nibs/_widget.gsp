<g:select from="${rosalind.NIB.list()}"
	  multiple="true"
          value="${method?.nibs*.id}"
	  name="nibs_list"
	  optionKey="id"
/>
