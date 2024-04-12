# MA234 Introduction to Big Data Notes

## 1. Introduction

### **机器学习 (Machine Learning) 的分类**

- 监督学习 (supervised learning)：利用一组已知类别的样本调整分类器的参数，使其达到所要求性能
  - 分类 (classification)：输出是离散的
  - 回归 (regression)：输出是连续的

- 无监督学习 (unsupervised learning)：根据类别未知(没有被标记)的训练样本解决模式识别
  - 密度估计 (density estimation)
  - 聚类 (clustering)：比如 K-Means，SOM
  - 降维 (dimensionality reduction)
- 半监督学习 (semi-supervised learning)：存在缺失数据
  - 比如填补有缺失的图像等
- 强化学习 (reinforcement learning)
  - 对抗类游戏的人工智能



### **数据表示**（PPT说的比较简洁清晰，直接放截图了）

<div align="center">
    <img src=".\\pic\\01_02.png" alt="" width="350">
    <img src=".\\pic\\01_03.png" alt="" width="350">
</div>




### **一些相关概念**

<img src=".\\pic\\01_04.png" alt="" width="600" align="left">



### 风险最小化策略 (Risk Minimization Strategy)

<img src=".\\pic\\01_05.png" alt="" width="550" align="left">



### 模型评估 (Model Assessment)

假设我们预测出了$y = \hat{f}(\bold{x})$，那么有以下 3 种误差 (error)，其中 $L(y, f(\bold{x}))$ 代表损失函数（见上面）：

- 训练误差 (Training error)：$R_{emp}(\hat{f}) = \frac{1}{n} \sum_{i = 1}^{n} L(y_i, \hat{f}(\bold{x_i}))$，说明了该问题的学习难度
- 测试误差 (Test error)：$e_{test}(\hat{f}) = \frac{1}{m} \sum_{j = n+1}^{n+m} L(y_j, \hat{f}(\bold{x_j}))$，说明了问题的预测能力
  - error rate：$e_{test}$
  - accuracy：$r_{test}$
  - $e_{test} + r_{test} = 1$
- 泛化误差 (Generalization error)：$R_{exp}(\hat{f}) = E_p[L(y, \hat{f}(\bold{x}))] = \int_{\mathcal{X}\times\mathcal{X}} L(y, \hat{f}(\bold{x}))P(\bold{x},y)d\bold{x}dy$ ，刻画了学习算法的经验风险与期望风险之间偏差和收敛速度



### 过拟合 (Overfitting)

过多模型参数导致，对训练集更好，但对测试集更差

应对方法：

- 正则化 (Regularization)：$\min\limits_{f \in F} \frac{1}{n} \sum_{i = 1}^{n} L(y_i, f(\bold{x_i}))+\lambda J(f)$，加入惩罚项$J(f)$，模型参数越多，$J(f)$越大，选择 合适的 $\lambda$ 可同时将经验风险和模型复杂性降至最低
- 交叉验证 (Cross-validation, CV)：将训练集拆分为训练子集和验证子集，使用训练集重复训练不同的模型，使用验证集选择验证误差最小的模型
  - Simple CV：将数据随机拆分为两个子集
  - K-fold CV：将数据随机拆分为大小相同的 K 个不相交子集，将 K − 1 个子集的并集视为训练集，将另一个子集视为验证集，重复执行此操作并选择平均验证误差最小的模型
  - Leave-one-out CV：在上一种情况下取 K = n



## 2. 数据预处理 (Data Preprocessing)

**数据种类**：表格数据 (Tabular data) （矩阵、向量、对象、关系），图形数据 (Graphical data)（网络、图形），多媒体数据 (Multi-media data)（文本、图像、视频、音频）

**属性 (Attributes) 类型**：离散的、连续的



### 基本的统计概念

- 平均数 (Mean)

- 中位数 (Median)

- 最大值 (Maximum)，最小值 (Minimum)

- 分位数 (Quantile)：中位数的推广，$k^{th}$ q-分位数 $x_q$：$P[\bold{X} < x_q] \leq k/q$

  四分位距 (interquartile range)：$IQR=Q3(75\%) − Q1(25\%)$

- 方差 (Variance) $Var(\bold{X})$，标准差 (Standard deviation)
- 众数 (Mode)

- 箱形图 (Box Plot)：如下图示例

  <img src=".\\pic\\02_01.png" alt="" width="250" align="left">



### 距离 (Distance)

#### **有关布尔变量的距离**

对于布尔变量，有 4 种距离

- symmetric distance $d(\bold{x_i},\bold{x_j}) = \frac{r+s}{q+r+s+t}$
- Rand index $Sim_{Rand}(\bold{x_i},\bold{x_j}) = \frac{q+t}{q+r+s+t}$
- non-symmetric distance $d(\bold{x_i},\bold{x_j}) = \frac{r+s}{q+r+s}$
- Jaccard index $Sim_{Jaccard}(\bold{x_i},\bold{x_j}) = \frac{q}{q+r+s}$

<img src=".\\pic\\02_02.png" alt="" width="180" align="left">

#### **闵可夫斯基 (Minkowski) 距离**

$$
d(\bold{x_i},\bold{x_j}) = \sqrt[h]{\sum\limits_{k=1}^p \left| \bold{x_{ik}} - \bold{x_{jk}} \right|^h }
$$
上述距离被称作 $L_h$ 范数 (norm)

- 正定： $d(\bold{x_i} , \bold{x_j}) \ge 0$ 等号成立当且仅当 $i = j$ 

- 对称：$d(\bold{x_i} , \bold{x_j}) = d(\bold{x_j} , \bold{x_i})$
- 三角不等式：$d(\bold{x_i} , \bold{x_j}) \le d(\bold{x_i},\bold{x_k}) + d(\bold{x_k},\bold{x_j})$

| $h$      | 名称                            |
| -------- | ------------------------------- |
| 1        | 曼哈顿距离 (Manhattan distance) |
| 2        | 欧式距离 (Euclidean distance)   |
| $\infty$ | Supremum distance               |

#### **余弦相似度 (Cosine Similarity)**

$$
cos(\bold{x_i},\bold{x_j}) = \frac{\bold{x_i}\cdot\bold{x_j}}{\|\bold{x_i}\|\|\bold{x_j}\|}
$$



### 数据缩放 (Data Scaling)

#### **数据缩放的原因**

- 为更好的性能：比如 SVM 中的 RBF 和 Lasso/岭回归中的惩罚项，需假设均值为 0 方差为 1
- 规范化不同的维度：身高（1.75m）和体重（70kg）

#### **4种数据放缩**

- Z-score scaling：$\bold{x_i^*} = \frac{\bold{x_i} - \hat{\mu}}{\hat{\sigma}}$，$\hat{\mu}$ 样本均值，$\hat{\sigma}$ 样本方差，**适用于最大值和最小值未知且数据分布良好**
- 0-1 scaling：$\bold{x_i^*} = \frac{\bold{x_i} - min_k\bold{x_k}}{max_k\bold{x_k} - min_k\bold{x_k}} \in [0,1]$，**适用于有界数据集，新加入数据要重新计算最大值最小值**
- Decimal scaling：$\bold{x_i^*} = \frac{\bold{x_i}}{10^k}$，**适用于适用于不同幅度的数据**
- Logistic scaling：$\bold{x_i^*} = \frac{1}{1+e^{-\bold{x_i}}}$，**适用于数据集中在原点附近，但会改变数据的分布**



### 数据离散化 (Data Discretization)

#### **离散化的原因**

- 提高鲁棒性 (robustness)：通过将异常值放入某些区间来消除异常值
- 能够对算法提供更合理的解释
- 降低存储和计算消耗

#### **分类**

- 无监督离散化 (Unsupervised discretization)
  - 等距离散化 (Equal-distance discretization)
  - 等频离散化 (Equal-frenquency discretization)
  - 基于聚类的离散化 (Clustering-based discretization)：进行分层聚类并得到分层结构（如使用 K-Means），并将同层中的样本放入相同的区间（比如家谱）
  - 基于 3σ 的离散化 (3σ-based discretization)：将样本分成 8 个间隔，需要先取对数
- 监督离散化 (Supervised discretization)
  - 基于信息增益的离散化 (Information Gain)：使用决策树进行分类
  - 卡方分箱离散化 (Chi Merge)：比较复杂一点，[参考链接](https://blog.csdn.net/fulk6667g78o8/article/details/120318205)



### 数据冗余 (Data Redundancy)

- 关联性强的变量 (Attribute)：比如 “出生日期” 和 “年龄”
- 通过关联分析确定数据冗余
  - 对于连续变量 A 和 B，计算相关系数 $\rho_{A,B}=\frac{\sum\limits_{i=1}^k (a_i-\overline{A})(b_i-\overline{B})}{k\hat{\sigma}_A\hat{\sigma}_B} \in [-1,1]$
  - 对于离散变量 A 和 B，计算$\chi^2$，越大说明关联性越小



### 数据缺失 (Missing Data)

#### **数据缺失的原因**

-  Missing Completely At Random (MCAR)：缺失数据的出现是随机事件
- Missing At Random (MAR)：取决于一些控制变量，例如，在青少年调查中，年龄大于 20 的样本会被排除
- Missing Not At Random (MNAR)：例如，表现不佳的员工被解雇后缺少数据

#### **应对方法**

- 简单的方法：删除样本（**适用于少量样本有数据缺失**），删除变量（**适用于某一变量缺失值较多**）
- 填补
  - 填补 0
  - 用数字类型的均值填充，用非数字类型的模数填充，**适用于MCAR**
    - 缺点：集中于均值而低估方差
    - 解决方案：填写不同的组
  - 用相似变量填充：引入自相关 (auto-correlation)
  - 用历史数据填充
  - K-Means 填充：使用完善的变量（无缺失值）计算数据的成对距离，然后用前 K 个最相似的完善数据的平均值填充缺失值，将自相关引入
  - 用期望最大化 (EM) 填充：引入隐藏变量并使用 MLE 估计缺失值
  - 随机填充 (Random filling)
    - Bayesian Bootstrap，Approximate Bayesian Bootstrap（略）
  - 基于模型的方法 (Model based methods)：将缺失变量视为 y，将其他变量视为 x，将没有缺失值的数据作为我们的训练集来训练分类或回归模型，将缺失值的数据作为测试集来预测缺失值
  - 插值填充 (Filling by Interpolation)

#### **哑变量 (Dummy Variables)**

哑变量，也称为虚拟变量或名义变量，是一种用于表示分类变量的变量类型。哑变量通常取值为 0 或 1，用来反映某个变量的不同属性。在一个有 n 个分类属性的自变量中，通常需要选择一个分类作为参照，从而产生 n-1 个哑变量。



### 离群值 (Outlier)

数据点看似来自不同的分布，或噪声数据，通常采用无监督检测

#### 离群值检测

- 上下 α 分位数之外的样本（取较小的 α，通常是 1%）

- 从箱形图观察

- 局部异常值因子 (LOF)：是一种基于密度的方法

  计算每个点 $\bold{x}$ 的密度，将每个点 $\bold{x}$ 的密度与其相邻点的密度进行比较

#### 相关概念

<div align="center">
    <img src=".\\pic\\02_03.png" alt="" width="500">
    <img src=".\\pic\\02_04.png" alt="" width="450">
</div>





## 3. 分类1 (Classification)

分类是一种监督学习，简而言之是对样本 $\bold{x}$ 预测它的标签 $y$

训练阶段：给定数据集 $D = \{ (\bold{x},y) \}$，分割为 $D = D_{train}\cup D_{test}$，找一个能最佳的关联 $\bold{x_{train}}$ 和 $y_{train}$ 的函数 $y = f(\bold{x})$，然后测试这个函数能在多大程度上适配 $\bold{x_{test}}$ 和 $y_{test}$ 

预测阶段：将训练得到的函数用于没有标签的样本 $\bold{x_{pred}}$，得到预测值 $y_{pred} = f(\bold{x_{pred}})$



### kNN (k-Nearest Neighbour)

对于待预测的 $\bold{x}$，找和它相邻的 k 个点，统计它们的标签，数量最多的标签作为 $\bold{x}$ 的标签。

```
Compute d(x,x_j) for each (x_j,y_j) in D_train
Sort the distances in an ascending order, choose the first k samples (x_1,y_1),...,(x_k,y_k)
Make majority vote y_pred = Mode(y_1,...,y_k)
```

**特点**：是最简单的监督学习算法，同时进行训练和测试，低偏差（bias，预测值和真实值之间的误差）、高方差（variance，预测值之间的离散程度）

**优点：对异常值不敏感，易于实现和并行化，适用于大型训练集，适用于对数据的先验知识非常有限的情况**

缺点：需要调参 k，占用储存空间大，计算量大且密集

#### 调参k —— 交叉验证 M-fold Cross-validation

将数据集分为 M 折（通常取 M = 5 或 10），设 $\kappa:\{1,...,N\}\rightarrow \{1,...,M\}$ 是随即分区索引映射，那么预测误差的 CV 估计值为 $CV(\hat{f},k) = \frac{1}{N} \sum\limits_{i = 1}^{N} L(y_i, \hat{f}^{-\kappa_i}(\bold{x_i},k))$ 

#### 分析

**时间复杂度**：O(mndK)，其中 n 是训练样本的数量，m 是测试样本的数量，d 是维度，K 是最近邻参数

**误差**：假设样本是 i.i.d (独立同分布) 的，对于任何测试样本 $\bold{x}$ 和足够小的 $δ$，总存在一个训练样本 $\bold{z}\in B(\bold{x},δ)$ （$\bold{x}$ 的标签与 $\bold{z}$ 的标签相同），则 1NN 误差为
$$
\epsilon = \sum\limits_{c=1}^C p_c(x)(1-p_c(z)) \xrightarrow{\delta\rightarrow0}1-\sum\limits_{c=1}^C p^2_c(x)
$$


### 决策树 (Decision Tree)

简介略，如图

但要注意，尽量将整个数据集划分成的每个部分所含杂质尽量少，下面介绍 3 种不纯度度量

<img src=".\\pic\\03_01.png" alt="" width="300" align="center">

#### 不纯度 (Impurity) 度量 - GINI 指数 (GINI Index)

- 节点 (Node) t 的 Gini 指数：$Gini(t) = 1-\sum\limits_{c=1}^C (p(c|t))^2$，$p(c|t)$ 是节点 t 中 c 类数据的比例

- $Gini(t)$ 的最大值是 $1-\frac{1}{C}$，在 $p(c|t) = \frac{1}{C}$ 时取到；最小值是 0，在对于某些 $c$ 有 $p(c|t) = 1$ 时取到

- 一条分支 (Split) 的 Gini 指数：$Gini_{split}=\sum\limits_{k=1}^K\frac{n_k}{n}Gini(k)$，其中 $n_k$ 是子节点 k 的样本数量，$n = \sum\limits_{k=1}^Kn_k$

- 选择使得 $Gini(t) - Gini_{split}$ 最大的分支

<img src=".\\pic\\03_02.png" alt="" width="500" align="center">

#### 不纯度度量 - 信息增益 (Information Gain)

- 节点 t 的熵 (Entropy)：$H(t) = -\sum\limits_{c=1}^C p(c|t)\log_{2}{p(c|t)}$ 

- $H(t)$ 的最大值是 $\log_{2}{C}$，在 $p(c|t) = \frac{1}{C}$ 时取到；最小值是 0，在对于某些 $c$ 有 $p(c|t) = 1$ 时取到

- 信息增益：$InfoGain_{split}=H(t) - \sum\limits_{k=1}^K\frac{n_k}{n}H(k)$，其中 $n_k$ 是子节点 k 的样本数量，$n = \sum\limits_{k=1}^Kn_k$ 

- 引入信息增益比 (Introduce information gain ratio)：$SplitINFO=- \sum\limits_{k=1}^K\frac{n_k}{n}\log_2{\frac{n_k}{n}}$ ，$InfoGainRatio=\frac{InfoGain_{split}}{SplitINFO}$ （C4.5 算法）

- 选择使得 $InfoGain_{split}$ 最大的分支（ID3 算法）

- 缺点：容易生成过多的子节点导致过拟合

#### 不纯度度量 - 误分类误差 (Misclassification Error)

- 节点 t 的误分类误差：$Error(t) = 1-\max_c p(c|t)$ 
- 最大值是 $1-\frac{1}{C}$，在 $p(c|t) = \frac{1}{C}$ 时取到；最小值是 0，在对于某些 $c$ 有 $p(c|t) = 1$ 时取到



例：对于二分类问题，3 种不纯度度量如下图

<img src=".\\pic\\03_03.png" alt="" width="500" align="center">

#### 决策树相关算法

| 算法                                      | 属性类型       | 不纯度度量 | 分割的子节点数量 | 目标属性类型   |
| ----------------------------------------- | -------------- | ---------- | ---------------- | -------------- |
| Iterative Dichotomiser 3 (ID3)            | 离散型         | 信息增益   | k ≥ 2            | 离散型         |
| C4.5                                      | 离散型、连续型 | 信息增益率 | k ≥ 2            | 离散型         |
| C5.0                                      | 离散型、连续型 | 信息增益率 | k ≥ 2            | 离散型         |
| Classification and Regression Tree (CART) | 离散型、连续型 | Gini 指数  | k = 2            | 离散型、连续型 |

<img src=".\\pic\\03_04.png" alt="" width="600" align="center">

对于较为复杂的树，可以采用剪枝 (Pruning) 的方法减小其复杂程度

<img src=".\\pic\\03_05.png" alt="" width="600" align="center">

#### 优缺点

优点：

- 容易解释和可视化：广泛应用于金融、医疗健康、生物等领域
- 易于处理缺失值（视为新数据类型）
- 可以扩展到回归

缺点：

- 由于采用贪心算法，容易得到局部最小值
- 决策的边界过于简单：例如与轴平行的线



### 朴素贝叶斯 (Naive Bayes)

基于贝叶斯定理和样本的条件独立假设而进行分类的算法

#### 贝叶斯定理

$$
P(Y|X) = \frac{P(X|Y)P(Y)}{P(X)}
$$

$P(Y)$ 先验 (prior) 概率分布，$P(X|Y)$ 似然 (likelihood) 函数，$P(X)$ 论据 (evidence)，$P(Y|X)$ 后验 (posterior) 概率分布

#### Naive Bayes Method

机器学习的目的是估计 $P(Y|X)$

假设 $X=\{X_1,...,X_d\}$（ $X$ 是 $d$ 维的）

对于给定的 $X=x$，$P(X=x)$ 对于 $Y$ 是独立的，由贝叶斯定理：
$$
P(Y|X=x)\propto P(X=x|Y)P(Y)
$$
假设 $X_1,...,X_d$ 是独立的，对于给定的 $Y=c$：
$$
P(X=x|Y=c) =  \prod\limits_{i=1}^d P(X_i=x_i|Y=c)
$$
那么，朴素贝叶斯算法就是以下面这个方式求 $y$ 的预测值 $\hat{y}$ 
$$
\hat{y}=\arg\max\limits_{c}P(Y=c)\prod\limits_{i=1}^d P(X_i=x_i|Y=c)
$$
对于数据集 $D = \{ (\bold{x_1},y_1),...,(\bold{x_n},y_n) \}$ 需要估计 $P(Y=c)$ 和 $P(X_i=x_i|Y=c)$ ，使用极大似然估计 (MLE)

MLE 估计 $P(Y=c)$ ：$P(Y=c)=\frac{\sum\limits_{i=1}^n I(y_i=c)}{n}$ 

当 $X_i$ 是离散的，且值域为 $\{v_1,...,v_k\}$，$P(X_i=v_k|Y=c)=\frac{\sum\limits_{i=1}^n I(x_i=v_k,y_i=c)}{\sum\limits_{i=1}^n I(y_i=c)}$ 

当 $X_i$ 是连续的，假设服从 $N(\mu,\sigma^2)$，$P(X_i=x|Y=c)=\frac{1}{\sqrt{2\pi}\sigma}e^{-\frac{(x-\mu)^2}{2\sigma^2}}$，MLE 估计参数 $\mu$ 和 $\sigma$ 

#### 优缺点

**优点**

- 对于离群值和缺失值表现较为稳定
- 鲁棒性：用于不相关的变量，$P(X|Y)$ 与 $Y$ 无关，因此对后验概率没有影响
- 即使不满足条件独立性假设，也可能优于更复杂的替代方案

**缺点**

- 违反条件独立性假设时，朴素贝叶斯的性能可能会更差
- 很大程度上取决于参数估计值的准确性



### 分类算法的模型评估

#### 混淆矩阵 (Confusion Matrix)

二分类问题的混淆矩阵示例

<img src=".\\pic\\03_06.png" alt="" width="200" align="left">

TP：true positive，TN：true negative，FP：false positive，FN：false negative

$\text{Accuracy} = \frac{TP+TN}{TP+FP+FN+TN}$ ，当样本分布不平衡时不是一个很好的参考指标

$\text{Precision} = \frac{TP}{TP+FP}$ 

$\text{Recall} = \frac{TP}{TP+FN}$，在医学领域很重要

$\text{Specifity} = \frac{TN}{FP+TN}$，负样本的 $\text{Recall}$ 

F Score：$F_\beta = \frac{(1+\beta^2)\text{Precision}\times\text{Recall}}{\beta^2\times\text{Precision}+\text{Recall}}$ 

#### Receiver Operating Characteristic (ROC) and AUC

目的是解决不同的类的分布不均衡。

为连续的预测值设置不同的阈值 t，比如若 $P(Y=1|X=X_i)>t$，则 $\hat{y}_i=1$ 

对不同的 t 值计算 $TPR = \frac{TP}{TP+FN}$，$FPR = \frac{FP}{FP+TN}$ 并绘制 ROC 曲线，ROC 越大，说明表现越好

AUC：ROC 曲线下的面积，越大越好，> 0.75 说明性能极佳

<img src=".\\pic\\03_07.png" alt="" width="300" align="left">

#### Kappa Coefficient 

<div align="center">
    <img src=".\\pic\\03_08.png" alt="" width="450">
    <img src=".\\pic\\03_09.png" alt="" width="450">
</div>

#### 多分类问题

- ROC 和 AUC 无法生效
- 混淆矩阵变为 $C \times C$，每个格子表示在预测类 i 和真实类 j 相交处的样本数量
- 多分类转化为多个二分类问题





## 4. 回归 (Regression)

从自变量 x 中预测因变量 y：$y = f(\bold{x})$ 或 $y = E(y|\bold{x})$ 

### 线性回归 (Linear Regression)

对于 n 个样本的数据集 $\{(\bold{x_i}, y_i)\}_{i=1}^n$，每个 $\bold{x_i}$ 是 $p$ 维的，设 $\bold{y} = (y_1,...,y_n)^T$， $\bold{w} = (w_1,...,w_p)$ 是代求的参数，$\bold{X} = [\bold{1_n},(\bold{x_1},...,\bold{x_n})^T]\in \mathbb{R}^{n\times(p+1)}$，$\epsilon = (\epsilon_1,...,\epsilon_n)^T\sim \mathcal{N}(\bold{0},\sigma^2\bold{I_n})$ 为误差，则有
$$
\bold{y} = \bold{X}\bold{w}+\epsilon
$$

#### (平凡)最小二乘 [(Ordinary) Least Square，OLS]

最小化残差平方和 (residual sum-of-squares)：
$$
RSS(\bold{w})=\sum\limits_{i=1}^n(y_i-w_0-w_1x_1-...-w_px_p)^2=\|\bold{y}-\bold{X}\bold{w}\|_2^2
$$

$$
\nabla_\bold{w}RSS(\bold{\hat{w}})=0 \quad \Rightarrow \quad \bold{\hat{w}}=(\bold{X}^T\bold{X})^{-1}\bold{X}^T\bold{y}
$$

$$
\bold{\hat{y}}=\bold{X}(\bold{X}^T\bold{X})^{-1}\bold{X}^T\bold{y}=\bold{P}\bold{y}
$$



其中，$\bold{P}$ 是一个投影矩阵，即 $\bold{P}^2=\bold{P}$ 

#### 评价指标

参数 $R^2 = 1-\frac{SS_{res}}{SS_{tot}}=(\frac{SS_{reg}}{SS_{tot}}\text{ for linear regression})$ ，越大说明回归效果越好

总平方和 (total sum of squares)： $SS_{tot}=\sum\limits_{i=1}^n(y_i-\overline{y})^2$  

回归平方和 (regression sum of squares)： $SS_{reg}=\sum\limits_{i=1}^n(\hat{y_i}-\overline{y})^2$  

残差平方和 (residual sum of squares)： $SS_{res}=\sum\limits_{i=1}^n(y_i-\hat{y_i})^2$  

#### 多元共线性 (Multicolinearity)

若 $\bold{X}$ 的列几乎线性相关（即多元共线），那么 $det(\bold{X}^T\bold{X})\approx0$，则 $(\bold{X}^T\bold{X})^{-1}$ 会非常大，那么得到的 $\bold{\hat{w}}$ 将会不准确。

#### Bias-Variance Decomposition

$L^2$ 损失中泛化误差 (generalization error) 的偏置方差分解 (bias-variance decomposition)：
$$
E_{train}R_{exp}(\hat{f}(x))=E_{train}E_P[(y-\hat{f}(x))^2|x]=Var(\hat{f}(x))+Bias^2(\hat{f}(x))+\sigma^2
$$
其中 $P=P(y|x)$ 

偏差 (Bias)：$Bias^2(\hat{f}(x))=E_{train}\hat{f}(x)-f(x)$，是对该模型进行预测的平均精度 (accuracy)

方差 (Variance)：$Var(\hat{f}(x))=E_{train}(\hat{f}(x)-E_{train}\hat{f}(x))^2$ ，是由于不同数据集而导致的模型预测的可变性（稳定性）

<img src=".\\pic\\04_01.png" alt="" width="300" align="left">

对于 kNN 回归（将 kNN 分类的众数改为平均数）有：

- 对于较小的 k，过拟合，低偏差，高方差
- 对于较大的 k，不足拟合，高偏差，低方差

### 正则化 (Regularization)

在维度过高，列很多的时候，方差会很大。减小一些系数或将其设置为零可以减少过拟合。选择更少的变量同样可以达到更高的效果。

#### Best-subset selection

对于所有 $k\in \{0, 1,...,p\}$ ，大小为 $k$ 的子集 $S_k\subset \{0, 1,...,p\}$，使得 $RSS(\bold{w})=\sum\limits_{i=1}^n(y_i-w_0-\sum\limits_{j\in S_k}w_jx_{ij})^2$ 

#### 使用惩罚项 (Penalties) 正则化

$$
\sum\limits_{i=1}^n(y_i-w_0-w_1x_1-...-w_px_p)^2+\lambda\|\bold{w}\|_q^q=\|\bold{y}-\bold{Xw}\|_2^2+\lambda\|\bold{w}\|_q^q
$$

q = 2 时，被称为岭回归；q = 1 时，被称为 LASSO 回归

#### 岭回归 (Ridge Regression)

$$
\bold{\hat{w}}=\arg\min\limits_\bold{w}\|\bold{y}-\bold{Xw}\|_2^2+\lambda\|\bold{w}\|_2^2
$$

$\lambda\ge0$ 是可以通过交叉验证 (CV) 调参的参数

该问题等价于下面的优化问题：
$$
\bold{\hat{w}}=\arg\min\limits_\bold{w}\|\bold{y}-\bold{Xw}\|_2^2,\quad \text{subject to }\|\bold{w}\|_2^2 \le \mu
$$
$\mu\ge0$ 是一个参数，较大的 $\lambda$ 对应较小的 $\mu$ 

岭回归的解如下
$$
\hat{\bold{w}}^{ridge}=(\bold{X}^T\bold{X}+\lambda\bold{I}_{p+1})^{-1}\bold{X}^T\bold{y} \\
\hat{\bold{y}}^{ridge}=\bold{X}\hat{\bold{w}}^{ridge}
$$
也可以使用 SVD 先将矩阵 $\bold{X}$ 分解，得到 $\bold{X} = \bold{P}\bold{D}\bold{Q}$，其中 $\bold{P}\in\mathbb{R}^{n\times(p+1)}$，$\bold{Q}\in\mathbb{R}^{(p+1)\times(p+1)}$，都是正交矩阵（即 $\bold{P}^T\bold{P} = \bold{I}$，$\bold{Q}^T\bold{Q} = \bold{I}$）$\bold{D}$ 是对角矩阵，$\bold{D} = diag(v_1,...,v_{p+1})$，那么得到岭回归的解为
$$
\hat{\bold{y}}^{ridge}=\bold{P}diag(\frac{v_1}{v_1^2+\lambda},...,\frac{v_{p+1}}{v_{p+1}^2+\lambda})\bold{P}^T\bold{y}
$$
（可以对比一下 OLS (ordinary least square) 得到的解 $\hat{\bold{y}}^{OLS}=\bold{P}\bold{P}^T\bold{y}$ 

#### 从贝叶斯视角看岭回归

<img src=".\\pic\\04_02.png" alt="" width="600" align="left">

#### 岭轨迹 (Ridge Trace)

以 $\lambda$ 为自变量，$\hat{\bold{w}}^{ridge}(\lambda)$ 为因变量的函数图像被称为岭轨迹

当 $\lambda \in (0,0.5)$ 时，岭轨迹很不稳定，一般取 $\lambda=1$ 

下图是一些岭轨迹图像：

<img src=".\\pic\\04_03.png" alt="" width="300" align="left">

- 轨迹稳定、绝对值较小的系数对 $y$ 的影响不大，如 (a)
- 轨迹稳定、绝对值较大的系数对 $y$ 的影响较大，如 (b)
- 两个变量的岭轨迹不稳定，但和是稳定的，说明这两个变量有多重共线性，如 (d)
- 所有变量的岭轨迹稳定，说明使用 OLS 具有良好的性能，如 (f)

#### LASSO 回归

$$
 \bold{\hat{w}}=\arg\min\limits_\bold{w}\|\bold{y}-\bold{Xw}\|_2^2+\lambda\|\bold{w}\|_1
$$

该问题等价于下面的优化问题：
$$
\bold{\hat{w}}=\arg\min\limits_\bold{w}\|\bold{y}-\bold{Xw}\|_2^2,\quad \text{subject to }\|\bold{w}\|_1 \le \mu
$$
$\mu\ge0$ 是一个参数，较大的 $\lambda$ 对应较小的 $\mu$ 

LASSO 回归的解为：
$$
\hat{w}_i^{lasso}=(|\hat{w}_i^{OLS}|-\lambda)_+sign(\hat{w}_i^{OLS})
$$
$\hat{w}_i^{lasso}$ 被称为 $\hat{w}_i^{OLS}$ 的软阈值 (soft thresholding)，其中记号 $(a)_+ = max(a,0)$ 代表 $a$ 的正部

LASSO 轨迹与岭轨迹定义相同，这些路径是分段线性的，可能多次穿过 x 轴。在实际应用中，通常会通过交叉验证 (CV) 的方式选择参数 $\lambda$ 。

下面是一种解 LASSO 回归的方法：

<img src=".\\pic\\04_04.png" alt="" width="600" align="left">

 与 LASSO 相关的一些模型：

- Elastic net：$\hat{\bold{w}}=\arg\min\limits_\bold{w} \|\bold{y}-\bold{X}\bold{w}\|_2^2+\lambda_1\|\bold{w}\|_2^2+\lambda_2\|\bold{w}\|_1$ 
- Group LASSO：$\hat{\bold{w}}=\arg\min\limits_\bold{w} \|\bold{y}-\bold{X}\bold{w}\|_2^2+\sum\limits_{g=1}^G\lambda_g\|\bold{w}_g\|_2$，其中 $\bold{w}=(\bold{w}_1,...,\bold{w}_g)$ 是 $\bold{w}$ 的一个分组

#### 最大后验估计 [Max A Posterior (MAP) Estimation]

给定参数 $\theta$，$\bold{y}$ 的条件分布为 $P(\bold{y}|\theta)$，且参数 $\theta$ 有先验分布 $P(\theta)$ 

那么 $\theta$ 对于给定 $\bold{y}$ 的后验分布 $P(\theta|\bold{y})\propto P(\bold{y}|\theta)P(\theta)$ 

MAP 选择后验最大的参数估计：
$$
\hat{\theta}^{MAP}=\arg\max\limits_{\theta}P(\theta|\bold{y})=\arg\max\limits_{\theta}(\log P(\bold{y}|\theta)+ \log P(\theta))
$$
不同的对数先验导致不同的惩罚（正则化），但一般情况不是这样：有些惩罚可能不是概率分布的对数，其他一些惩罚取决于数据（先验独立于数据）



### 回归模型的评价

- Mean absolute error (MAE)：$MAE=\frac{1}{n}\sum\limits_{i=1}^n|y_i-\hat{y_i}|$ 

- Mean square error (MSE)：$MSE=\frac{1}{n}\sum\limits_{i=1}^n(y_i-\hat{y_i})^2$ 

- Root mean square error (RMSE)：$RMSE=\sqrt{MSE}$ 

- 相关系数 (Coefficient of Determination) $R^2 = 1-\frac{SS_{res}}{SS_{tot}}=(\frac{SS_{reg}}{SS_{tot}}\text{ for linear regression})$ ，越大说明回归效果越好

  总平方和 (total sum of squares)： $SS_{tot}=\sum\limits_{i=1}^n(y_i-\overline{y})^2$  

  回归平方和 (regression sum of squares)： $SS_{reg}=\sum\limits_{i=1}^n(\hat{y_i}-\overline{y})^2$  

  残差平方和 (residual sum of squares)： $SS_{res}=\sum\limits_{i=1}^n(y_i-\hat{y_i})^2$  

- 校正*系数*的决定系数 (Adjusted Coefficient of Determination)：$R_{adj}^2=1-\frac{(1-R^2)(n-1)}{n-p-1}$，其中 $n$ 代表样本数量，$p$ 代表样本的维数，越大说明回归效果越好

  当加入重要样本时，$R_{adj}^2$ 变大而 $SS_{res}$ 变小；当加入不重要样本时，$R_{adj}^2$ 可能变小而 $SS_{res}$ 可能变大

  实际上，$1-R_{adj}^2=\frac{\hat{\sigma}^2}{S^2}$，其中 $\hat{\sigma}^2=\frac{1}{n-p-1}\sum\limits_{i=1}^n(y_i-\hat{y_i})^2$，$S^2=\frac{1}{n-1}\sum\limits_{i=1}^n(y_i-\overline{y})^2$ （若 $\bold{w}=0$，则有 $(n-p-1)\frac{\hat{\sigma}^2}{\sigma^2}\sim \chi^2_{n-p-1}$ 和 $(n-1)\frac{S^2}{\sigma^2}\sim \chi^2_{n-1}$）





## 5. 分类2

