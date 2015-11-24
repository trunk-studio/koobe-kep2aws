
import groovy.io.FileType

def ant = new AntBuilder()

def list = []

new File('Cover').eachFileRecurse(FileType.DIRECTORIES) {
	dir ->

	if (dir.name?.length() == 36) {
		list << dir
	}
}

list.each {
	dir ->

	def dirName = dir.name
	def dig1 = dirName[0]
	def dig2 = dirName[1]
	def dig3 = dirName[2]

	def newDir = "Cover2/${dig1}/${dig2}/${dig3}/"

	ant.mkdir(dir: newDir)

	ant.move(file: dir, todir: newDir)		
}
