# Vehicle Fine Records Management System

### Description
Sharing vehicle fine records poses some challenges regarding concealing the sensitive information as well as secured, transparent, and efficient distribution of the non-sensitive information. Thus, we propose a management system of vehicle fine records that implement a hybrid blockchain paired with security features that ensures decentralization, transparency, immutability, security, and improved performance. To achieve these goals, we used:

1. Hybrid Blockchain architecture for decentralization for the decentralization of records.
2. SHA-256 for hashing.
3. Elliptic Curve Cryptography (ECIES) for confidentiality.
4. Digital Signature (ECDSA) for integrity, authentication, and non-repudiation.

### Technologies used

| Technology | Justification |
| ---------- | ------------- |
| Java(JDK 11) | In-built modules for security features, access modifiers, cross-platform support, object-oriented programming. JDK 11 is LTS so get support for a long time. |
| IntelliJ IDEA | Popular IDE for Java development with many features enabling to maintain and modularize code easily. |
| GitHub | Version Controlling using Git. Secured. Easy to use. Free Hosting. |
| SHA-256 (hashing) | Very secured. Recommended by US government agencies to protect sensitive information. |
| ECC (encryption), ECDSA (digital signature) | Use smaller keys to get the same levels of security as RSA. Suitable for cryptography done on less powerful devices. |

### System Design
![System Design diagram](/system-design.jpg)

### Engineering Practices

| Practices | Description |
| --------- | ----------- |
| Modularity | We have divided the project into modules for easier maintenance of code. For each feature implementation, we have divided the code into at least one class file. This has enabled us to reuse the codes and maintain a proper structure. |
| Clean code | We tried to follow clean code conventions but chose appropriate variable names and division of methods for handling each sub-task. Additionally, we have included comments to enhance the readability of our codes. |
| Version Controlling | Since the development and testing were distributed among the team members, we used version controlling for effective collaboration. The repository was updated and maintained and made public for review. |
| Project Management | We divided the tasks of the project and distributed handling the aspects of our project according to our individual strengths. This promoted synergy among the team members. We also used tools like Gantt Charts to schedule the activities as effectively as we could. |
