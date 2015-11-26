def results = []

def ant = new AntBuilder()

def synccmd = "s3cmd sync book s3://koobe-e7read/"

def process = synccmd.execute()

process.waitForProcessOutput(System.out, System.err)
process.waitFor()

/*
new File('step4.txt').eachLine { line ->
}
*/

