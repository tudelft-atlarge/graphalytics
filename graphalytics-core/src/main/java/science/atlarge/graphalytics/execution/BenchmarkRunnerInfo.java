/*
 * Copyright 2015 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package science.atlarge.graphalytics.execution;

import akka.actor.ActorRef;
import science.atlarge.graphalytics.domain.benchmark.BenchmarkRun;
import science.atlarge.graphalytics.report.result.BenchmarkRunResult;

/**
 * @author Wing Lung Ngai
 */
public class BenchmarkRunnerInfo {

    public BenchmarkRunnerInfo(BenchmarkRun benchmarkRun, Process process) {
        this.benchmarkRun = benchmarkRun;
        this.process = process;
    }

    boolean isInitialized;
    boolean isExecuted;
    boolean isValidated;
    boolean isCompleted;
    boolean isTerminated;

    BenchmarkRunResult benchmarkRunResult;
    BenchmarkRun benchmarkRun;
    Process process;
    ActorRef actor;

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }

    public BenchmarkRun getBenchmarkRun() {
        return benchmarkRun;
    }

    public void setBenchmarkRun(BenchmarkRun benchmarkRun) {
        this.benchmarkRun = benchmarkRun;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public ActorRef getActor() {
        return actor;
    }

    public void setActor(ActorRef actor) {
        this.actor = actor;
    }

    public BenchmarkRunResult getBenchmarkRunResult() {
        return benchmarkRunResult;
    }

    public void setBenchmarkRunResult(BenchmarkRunResult benchmarkRunResult) {
        this.benchmarkRunResult = benchmarkRunResult;
    }
}
