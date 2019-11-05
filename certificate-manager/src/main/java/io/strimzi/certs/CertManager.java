/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.certs;

import java.io.File;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

public interface CertManager {
    /**
     * Generate a self-signed certificate
     *
     * @param keyFile path to the file which will contain the private key
     * @param certFile path to the file which will contain the self signed certificate
     * @param sbj subject information
     * @param days certificate duration
     * @throws IOException If an input or output file could not be read/written.
     */
    void generateSelfSignedCert(File keyFile, File certFile, Subject sbj, int days) throws IOException;

    /**
     * Generate a self-signed certificate
     *
     * @param keyFile path to the file which will contain the private key
     * @param certFile path to the file which will contain the self signed certificate
     * @param days certificate duration
     * @throws IOException If an input or output file could not be read/written.
     */
    void generateSelfSignedCert(File keyFile, File certFile, int days) throws IOException;

    /**
     * Renew a new self-signed certificate, keeping the existing private key
     * @param keyFile path to the file containing the existing private key
     * @param certFile path to the file which will contain the new self signed certificate
     * @param sbj subject information
     * @param days certificate duration
     * @throws IOException If an input or output file could not be read/written.
     */
    void renewSelfSignedCert(File keyFile, File certFile, Subject sbj, int days) throws IOException;

    /**
     * Add the provided certificate to the truststore which is created if it doesn't exist
     *
     * @param certFile path to the file which will contain the self signed certificate to store
     * @param certAlias certificate alias in the store
     * @param trustStoreFile path to the file related to the truststore
     * @param trustStorePassword password for protecting the truststore
     * @throws IOException If an input or output file could not be read/written.
     * @throws CertificateException if any problems reading the certificate file in X509 format
     * @throws KeyStoreException if any problems with reading/writing the truststore
     * @throws NoSuchAlgorithmException if specified algorithm for truststore is not supported
     */
    void addCertToTrustStore(File certFile, String certAlias, File trustStoreFile, String trustStorePassword)
            throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException;

    /**
     * Remove entries with provided aliases from the truststore
     *
     * @param aliases aliases to remove
     * @param trustStoreFile path to the file related to the truststore
     * @param trustStorePassword password for protecting the truststore
     * @throws IOException If an input or output file could not be read/written.
     * @throws CertificateException if any problems reading the certificate file in X509 format
     * @throws KeyStoreException if any problems with reading/writing the truststore
     * @throws NoSuchAlgorithmException if specified algorithm for truststore is not supported
     */
    void deleteFromTrustStore(List<String> aliases, File trustStoreFile, String trustStorePassword)
            throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException;

    /**
     * Generate a certificate sign request
     *
     * @param keyFile path to the file which will contain the private key
     * @param csrFile path to the file which will contain the certificate sign request
     * @param sbj subject information
     * @throws IOException If an input or output file could not be read/written.
     */
    void generateCsr(File keyFile, File csrFile, Subject sbj) throws IOException;

    /**
     * Generate a certificate signed by a Certificate Authority
     *
     * @param csrFile path to the file containing the certificate sign request
     * @param caKey path to the file containing the CA private key
     * @param caCert path to the file containing the CA certificate
     * @param crtFile path to the file which will contain the signed certificate
     * @param days certificate duration
     * @throws IOException If an input or output file could not be read/written.
     */
    void generateCert(File csrFile, File caKey, File caCert, File crtFile, int days) throws IOException;

    /**
     * Generate a certificate signed by a Certificate Authority
     *
     * @param csrFile path to the file containing the certificate sign request
     * @param caKey path to the file containing the CA private key
     * @param caCert path to the file containing the CA certificate
     * @param crtFile path to the file which will contain the signed certificate
     * @param sbj subject information
     * @param days certificate duration
     * @throws IOException If an input or output file could not be read/written.
     */
    void generateCert(File csrFile, File caKey, File caCert, File crtFile, Subject sbj, int days) throws IOException;

    /**
     * Generate a certificate signed by a Certificate Authority
     *
     * @param csrFile path to the file containing the certificate sign request
     * @param caKey CA private key bytes
     * @param caCert CA certificate bytes
     * @param crtFile path to the file which will contain the signed certificate
     * @param days certificate duration
     * @throws IOException If an input or output file could not be read/written.
     */
    void generateCert(File csrFile, byte[] caKey, byte[] caCert, File crtFile, int days) throws IOException;

    /**
     * Generate a certificate signed by a Certificate Authority
     *
     * @param csrFile path to the file containing the certificate sign request
     * @param caKey CA private key bytes
     * @param caCert CA certificate bytes
     * @param crtFile path to the file which will contain the signed certificate
     * @param sbj subject information
     * @param days certificate duration
     * @throws IOException If an input or output file could not be read/written.
     */
    void generateCert(File csrFile, byte[] caKey, byte[] caCert, File crtFile, Subject sbj, int days) throws IOException;
}
