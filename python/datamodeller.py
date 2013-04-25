import numpy as np
import scipy
import sys
from sklearn import linear_model,svm,naive_bayes,neighbors
from sklearn.metrics import confusion_matrix
from sklearn.metrics import precision_recall_fscore_support,classification_report
from sklearn import preprocessing as pp
from sklearn import cross_validation as cv
from sklearn.decomposition import SparsePCA
from sklearn.feature_extraction.text import TfidfTransformer

def writeToFile(fileName,xTestAuthorPaperids,yPred):
    outputFile = open("../files/"+fileName+".csv", 'w+')
    rows = len(yPred)
    for i in range(0,rows):
        outputFile.write(str(int(xTestAuthorPaperids[i,0])) +","+str(int(xTestAuthorPaperids[i,1]))+","+str(int(yPred[i]))+"\n")
    outputFile.close()
    
class DataModeller:
    
    def __init__(self, training_file, test_file):
        self.training_file = training_file
        self.test_file = test_file
        
    def runAnalysis(self):
        
        trainingData = np.loadtxt(open(self.training_file, 'rb'), delimiter = ',', skiprows = 0);
        testData = np.loadtxt(open(self.test_file,'rb'), delimiter = ',', skiprows = 0);
        
        xTrain =  trainingData[:, 2:trainingData.shape[1]-1]
        yTrain = trainingData[:,trainingData.shape[1]-1]
                  
        xTest = testData[:, 2:testData.shape[1]]
        xTestAuthorPaperIds = testData[:, 0:2]
        
        
        print "Training dimension -> ",xTrain.shape
        print "Testing dimension ->  ",xTest.shape
        

        #Logistic Regression classification
        print "Log regression";
        logreg = linear_model.LogisticRegression(penalty="l1",C=0.5,intercept_scaling=2);
        logreg.fit(xTrain,yTrain);
        yPred = logreg.predict(xTest);                
        writeToFile("logRegPredict", xTestAuthorPaperIds, yPred);                        
      
               
        
     
        
        
        #SVM based classification
        print "SVM";
        svmclf = svm.SVC(C=8.0,gamma=0.10,kernel='rbf',probability=True,shrinking=True);
        svmclf.fit(xTrain,yTrain);
        yPred = svmclf.predict(xTest);
        writeToFile("svmPredict", xTestAuthorPaperIds, yPred);
        
if __name__ == '__main__':
    if len(sys.argv) < 2:
        print 'Please provide the training file and test file'
        print 'python datamodeller.py <training-file-path> <test_file>'
        sys.exit(1)
    training_file = sys.argv[1]
    test_file = sys.argv[2]
    model = DataModeller(training_file, test_file)
    model.runAnalysis()
        
        
