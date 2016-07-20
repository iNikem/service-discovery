package uni.tartu.algorithm

import groovy.util.logging.Slf4j

/**
 * author: lkokhreidze
 * date: 4/27/16
 * time: 3:08 PM
 **/

@Slf4j
class DocumentIdSelector {
  public static final int DEFAULT_DOCUMENT_SIZE = 1000

  private int numberOfDocuments
  private final Random rnd = new Random()

  DocumentIdSelector(int inputDataSetSize, int configuredDocumentSize) {
    calculateNumberOfDocuments(inputDataSetSize, configuredDocumentSize)
  }

  public int nextDocumentToUse() {
    rnd.nextInt(numberOfDocuments) + 1
  }

  private void calculateNumberOfDocuments(int inputDataSetSize, int configuredDocumentSize) {
    if (configuredDocumentSize == 0) {
      configuredDocumentSize = DEFAULT_DOCUMENT_SIZE
      log.info("Document size is not configured, using default {}", DEFAULT_DOCUMENT_SIZE)
    } else {
      log.info("Using configured document size {}", configuredDocumentSize)
    }

    numberOfDocuments = inputDataSetSize / configuredDocumentSize as int
    log.info("Input data size is {}. Will use {} documents", inputDataSetSize, this.numberOfDocuments)
  }
}
