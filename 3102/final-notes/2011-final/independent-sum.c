int maxSumIndependent(int givenArray[], int n) {
	int i, bestSolution[n], bestSolutionIncluding[n], bestSolutionExcluding[n];
	bestSolutionIncluding[n - 1] = givenArray[n - 1];
	bestSolutionExcluding[n - 1] = 0;
	bestSolution = max(bestSolutionIncluding[n - 1], bestSolutionExcluding[n - 1]);
	for (i = n - 2; i >= 0; i--) {
		bestSolutionIncluding[i] =
		bestSolutionExcluding[i] =
		bestSolution             = max(bestSolutionIncluding[i], bestSolutionExcluding[i]);

	}
	printf("The best possible value is %d\n", bestSolution[0]);
	// output numbers
	printf("The numbers which achieve this sum are: \n");
	for (i = 0; i < n; i++) {
		if (bestSolutionIncluding[i] > bestSolutionExcluding[i]) {
			// complete
		} else {
			// complete
		}
	}
	printf("\n");
}
