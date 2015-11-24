def results = []

new File('step1.txt').eachLine { line ->

	def tokens = line.split('\t')

	def uuid = tokens[0].toLowerCase()

	def uuid0 = uuid[0]
	def uuid1 = uuid[1]
	def uuid2 = uuid[2]
	
	def name = tokens[1].trim()

	def kepPath = "KepRaw/${uuid0}/${uuid1}/${uuid}.kep"
	def epubPath = "EpubRaw/${uuid0}/${uuid1}/${uuid}.epub"

	def ws_kep_path  = "webbookstore/kep/${uuid0}/${uuid1}/${uuid2}/${uuid}.kep"
	def ws_epub_path = "webbookstore/epub/${uuid0}/${uuid1}/${uuid2}/${uuid}.epub"
	def ws_pdf_path  = "webbookstore/pdf/${uuid0}/${uuid1}/${uuid2}/${uuid}.pdf"

	def kepFile = new File(kepPath)
	def epubFile = new File(epubPath)

	def ws_kep_file = new File(ws_kep_path)
	def ws_epub_file = new File(ws_epub_path)
	def ws_pdf_file = new File(ws_pdf_path)

	//println kepPath
	//println epubPath
	println "${uuid}, kep: ${kepFile.exists()?'T':'F'}, epub: ${epubFile.exists()?'T':'F'}, ws_kep: ${ws_kep_file.exists()?'T':'F'}, ws_epub: ${ws_epub_file.exists()?'T':'F'}, ws_pdf: ${ws_pdf_file.exists()?'T':'F'}, ${name}"


	if (kepFile.exists()) {
		results << "${uuid}\t${kepFile}"
	}
}

new File('step2.txt').text = results.join('\n')
