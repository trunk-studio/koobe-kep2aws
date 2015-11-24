def results = []

def ant = new AntBuilder()

new File('step2.txt').eachLine { line ->

	def tokens = line.split('\t')

	def uuid = tokens[0].toLowerCase()

	def uuid0 = uuid[0]
	def uuid1 = uuid[1]
	def uuid2 = uuid[2]

	def kepPath = tokens[1].trim()

	def kepFile = new File(kepPath)

	def zipFile = new java.util.zip.ZipFile(kepFile)

	def newDir = "book/${uuid0}/${uuid1}/${uuid2}/${uuid}/"

	ant.mkdir(dir: newDir)

	def pdfFile = new File(newDir, "${uuid}.pdf")

	zipFile.entries().each {
		if (!it.isDirectory() && it.name.endsWith('.pdf')) {
			pdfFile << zipFile.getInputStream(it)

			results << "${uuid}\t${pdfFile}"
		}
	}
}

new File('step3.txt').text = results.join('\n')
