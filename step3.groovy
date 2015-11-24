def results = []

def ant = new AntBuilder()

new File('step3.txt').eachLine { line ->

	def tokens = line.split('\t')

	def uuid = tokens[0].toLowerCase()

	def uuid0 = uuid[0]
	def uuid1 = uuid[1]
	def uuid2 = uuid[2]

	def pdfPath = tokens[1].trim()

	def bookDir = "book/${uuid0}/${uuid1}/${uuid2}/${uuid}/"

	def pdfFile = new File(pdfPath)

	def pagesDir = "book/${uuid0}/${uuid1}/${uuid2}/${uuid}/pages"

	ant.mkdir(dir: pagesDir)

	def pdfcmd = "gs -dSAFER -sDEVICE=jpeg -dJPEGQ=85 -r300 -sOutputFile=${pagesDir}/%d.jpg -dBATCH -dNOPAUSE ${bookDir}/${uuid}.pdf"

	println "Converting PDF: ${uuid}.pdf"
	println "${pdfcmd}"

	def process = pdfcmd.execute()

	process.waitForProcessOutput(System.out, System.err)
	process.waitFor()

	if (process.exitValue() == 0) {

		def imcmd = "convert ${pagesDir}/1.jpg -resize 500x500 ${bookDir}/cover.jpg"

		println "Generate Cover thumbnails"
		println "${imcmd}"

		def process2 = imcmd.execute()
        
		process2.waitForProcessOutput(System.out, System.err)
		process2.waitFor()

		def pagesN = new File(pagesDir).listFiles().findAll { it.name.endsWith('.jpg') }.size()

		def sqlUpdate = "update Books set isS3Ready=1, totalPages=${pagesN} where eBookGuid=UPPER('${uuid}');"

		results << sqlUpdate
	}
	else {
		ant.delete(dir: pagesDir)
	}
}

new File('step4.txt').text = results.join('\n')
