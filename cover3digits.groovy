
import groovy.io.FileType

def ant = new AntBuilder()

def list = []

new File('Cover').eachFileRecurse(FileType.FILES) {
	file ->
println file
	if (file.name?.endsWith('.jpg')) {
		list << file
	}
}

list.each {
	file ->

	def fileName = file.name
	def dig1 = fileName[0]
	def dig2 = fileName[1]
	def dig3 = fileName[2]

	def newDir = "Cover2/${dig1}/${dig2}/${dig3}/"

	println newDir

	//ant.mkdir(dir: newDir)

	//ant.move(file: dir, todir: newDir)		
}
